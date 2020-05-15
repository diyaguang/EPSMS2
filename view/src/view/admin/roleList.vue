<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix" style="line-height: 39px;">
        <span>角色列表</span>
        <el-button-group style="float: right; padding: 5px 0; margin-right: 10px">
          <el-button type="primary" icon="el-icon-document-add" @click="handleAdd"></el-button>
          <el-button type="primary" icon="el-icon-document-delete" @click="handleBatchDelete"></el-button>
          <el-button type="primary" icon="el-icon-document-copy"></el-button>
          <el-button type="primary" icon="el-icon-printer"></el-button>
        </el-button-group>
      </div>
      <el-table
        ref="multipleTable"
        :height="tableHeight"
        @selection-change="handleSelectionChange"
        v-loading="loading"
        :data="roleData"
        style="width: 100%"
      >
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          fixed
          prop="code"
          label="Code"
          width="120">
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色名称"
          width="120">
        </el-table-column>
        <el-table-column
          prop="description"
          label="描述"
          width="320">
        </el-table-column>
        <el-table-column
          prop="statusVo.name"
          label="状态"
          width="120">
        </el-table-column>
        <el-table-column label="功能" width="120">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleFunctionEdit(scope.$index, scope.row)">配置
            </el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-top: 10px"
                     @size-change="handleSizeChange"
                     @current-change="handleCurrentChange"
                     :current-page="currentPage"
                     :page-sizes="[10, 20, 30, 50]"
                     :page-size="10"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="currentTotal">
      </el-pagination>
    </el-card>

    <el-dialog :title="roleInfoOpTitle" :visible.sync="roleInfoFormVisible" width="500px">
      <el-form :model="currentRole">
        <el-form-item label="编号" :label-width="formLabelWidth">
          <el-input v-model="currentRole.code" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="currentRole.roleName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input v-model="currentRole.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="roleInfoFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleRoleInfoOp">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="角色功能配置" :visible.sync="roleFunctionFormVisible" width="550px">
      <div id="div">
        <el-tree
          ref="tree"
          :data="baseFunctionData"
          show-checkbox
          :default-checked-keys="currentRoleFunctions"
          :default-expand-all="true"
          :props="defaultProps"
          node-key="key">
        </el-tree>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="roleFunctionFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleRoleFunctionSave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import Vue from "vue";

  export default {
    name: "roleList.vue",
    data() {
      return {
        roleInfoOpType: '',
        roleInfoOpTitle: '',
        roleInfoFormVisible: false,
        roleFunctionFormVisible: false,
        loading: true,
        currentPage: 1,
        currentPageSize: 10,
        currentTotal: 0,
        baseFunctionData: [],
        currentRoleFunctions: [],
        currentRole: {},
        formLabelWidth: '50px',
        multipleSelection: [],
        tableHeight: 250,
        currentOpRoleId: '',
        roleData: [],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    created: function () {
      this.initData();
    },
    mounted: function () {
      setTimeout(() => {
        this.tableHeight = window.innerHeight - this.$refs.multipleTable.$el.offsetTop - 150;
      }, 100)
      //此处需要通过延迟方法来设置值，不然会出现值已更新，但页面没更新的问题
      //this.$refs.table.$el.offsetTop：表格距离浏览器的高度
    },
    methods: {
      handleRoleFunctionSave() {
        //获取选择的子节点
        var dataNode = this.$refs.tree.getCheckedKeys(true);
        //获取选中（半选中）的父节点
        var dataHalfNode = this.$refs.tree.getHalfCheckedKeys();
        dataNode = dataNode.concat(dataHalfNode);
        if (dataNode.length == 0){
          this.$confirm('已选功能为空, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.updateRoleFunctionLink();
          }).catch(() => {

          });
        }else {
          this.updateRoleFunctionLink();
        }
      },
      updateRoleFunctionLink(data) {
        var that = this;
        var saveLinkUrl = "/system/role/roleFunctionLink/modify?roleId=" + that.currentOpRoleId;
        /*     var data = {
               funcIds: data
             };*/
        this.$ajax.post(saveLinkUrl, data)
          .then(function (response) {
            if (response.data.code == 200) {
              that.$message({
                message: '功能关联保存成功!',
                type: 'success'
              });
              that.initData();
              that.userRoleFormVisible = false;
            } else {
              that.$message({
                message: '功能关联保存失败!' + response.data.msg,
                type: 'error'
              });
            }
          })
          .catch(function (error) {
            console.log(error);
          });
        this.roleFunctionFormVisible = false;
      },
      handleRoleInfoOp() {
        if (this.roleInfoOpType == "add") {
          this.handleInsert();
        } else if (this.roleInfoOpType == "update") {
          this.handleUpdate();
        }
      },
      handleFunctionEdit(index, row) {

        //获取当前用户的角色列表，循环key 为 数组，表示已选
        this.currentOpRoleId = row.id;
        this.currentRoleFunctions = [];
        row.functions.forEach((item, index, array) => {
          this.currentRoleFunctions.push(item.id);
        });
        /*
        说明：以后this.$refs为undefined的时候，不妨考虑下是不是真实dom还没有形成
        可以用 this.$nextTick包裹一下试试，这也是 this.$nextTick我目前使用到的地方，
        大致来说就是一个虚拟dom变成真实之后的一个回调，只有在回调里面才能获取到$refs，问题自然就解决了
        */
        this.$nextTick(() => {
          this.$refs.tree.setCheckedKeys(this.currentRoleFunctions);
        })
        this.roleFunctionFormVisible = true;
      },
      handleSizeChange(val) {
        this.currentPageSize = val;
        this.initData();
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        this.initData();
      },
      initData() {
        this.loading = true;
        var _this = this;
        var getMainDataUrl = "/system/role/listShow?page=" + this.currentPage + "&pageSize=" + this.currentPageSize;
        this.$ajax.get(getMainDataUrl)
          .then(function (response) {
            _this.roleData = response.data.data;
            _this.currentTotal = response.data.count;
          })
          .catch(function (error) {
            console.log(error);
          });
        this.loading = false;
        //获取功能菜单基础数据
        var getFunctionDataUrl = "/system/function/listForOp";
        this.$ajax.get(getFunctionDataUrl)
          .then(function (response) {
            _this.baseFunctionData = response.data;

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleRoleInfoOp() {
        if (this.roleInfoOpType == "add") {
          this.handleInsert();
        } else if (this.roleInfoOpType == "update") {
          this.handleUpdate();
        }
      },
      handleAdd() {
        this.roleInfoFormVisible = true;
        this.currentRole = {};
        this.roleInfoOpType = "add";
        this.roleInfoOpTitle = "添加角色";
      },
      handleEdit(index, row) {
        this.roleInfoFormVisible = true;
        //this.currentUser = row;
        //this.currentUser = Object.assign({}, row);  //对象进行浅复制(只复制属性和值) 这个处理对于嵌套的对象是不起作用的
        this.currentRole = JSON.parse(JSON.stringify(row));  //对象进行浅复制(只复制属性和值)
        this.roleInfoOpType = "update";
        this.roleInfoOpTitle = "编辑角色信息";
      },
      handleDelete(index, row) {
        this.$confirm('删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var that = this;
          var deleteDataUrl = "/system/role/delete?roleId=" + row.id;
          this.$ajax.get(deleteDataUrl)
            .then(function (response) {
              if (response.data.code == "200") {
                that.$message({
                  message: '数据删除成功!',
                  type: 'success'
                });
                that.initData();
              } else {
                that.$message({
                  message: '数据删除失败!' + response.data.msg,
                  type: 'error'
                });
              }
            })
            .catch(function (error) {
              console.log(error);
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleBatchDelete() {
        let size = this.multipleSelection.length;
        if (size > 0) {
          this.$confirm('将删除选中的 ' + size + ' 条记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //循环删除
            this.multipleSelection.forEach((item, index, array) => {
              var that = this;
              var deleteDataUrl = "/system/role/delete?roleId=" + item.id;
              this.$ajax.get(deleteDataUrl)
                .then(function (response) {
                  if (response.data.code == 1) {
                  } else {

                  }
                })
                .catch(function (error) {
                  console.log(error);
                });
            });

            //删除完成，清空选中的记录，刷新数据

            this.$message({
              message: '数据删除成功!',
              type: 'success'
            });
            this.initData();
            this.multipleSelection = [];
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        } else {
          this.$message({
            type: 'info',
            message: '请先选择要删除的记录'
          });
        }
      },
      handleUpdate() {
        var _this = this;
        var updateDataUrl = "/system/role/update";
        //添加更新信息
        this.currentRole.opUserId = Vue.prototype.CurrentUser.id;
        this.currentRole.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(updateDataUrl, this.currentRole)
          .then(function (response) {
            console.log(response.data);
            if (response.data.code == "200") {
              _this.$message({
                message: '数据更新成功!',
                type: 'success'
              });
              _this.roleInfoFormVisible = false;
              _this.initData();
            } else {
              _this.$message({
                message: '数据更新失败!' + response.data.msg,
                type: 'error'
              });
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleInsert() {
        var _this = this;
        var insertDataUrl = "/system/role/insert";
        this.currentRole.opUserId = Vue.prototype.CurrentUser.id;
        this.currentRole.isDel = 0
        this.currentRole.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(insertDataUrl, this.currentRole)
          .then(function (response) {
            if (response.data.code == "200") {
              _this.$message({
                message: '数据新增成功!',
                type: 'success'
              });
              _this.roleInfoFormVisible = false;
              _this.initData();
            } else {
              _this.$message({
                message: '数据新增失败!' + response.data.msg,
                type: 'error'
              });
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
  }
</script>

<style scoped>
  .text {
    font-size: 10pt;
  }

  .item {
    margin-top: 5px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 100%;
  }

  .el-card /deep/ .el-card__header {
    padding: 5px 5px 5px 20px;
    font-size: 10pt;

  }

  .el-card /deep/ .el-card__body {
    padding: 10px 10px 10px 20px;

  }

  #div {
    height: 300px;
    overflow-x: hidden;
    overflow-y: scroll;
  }
</style>
