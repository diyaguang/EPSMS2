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
        :data="functionData"
        style="width: 100%"
      >
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="funcCode"
          label="编号"
          width="80">
        </el-table-column>
        <el-table-column
          prop="funcName"
          label="功能名称"
          width="100">
        </el-table-column>
        <el-table-column
          prop="type"
          label="类型"
          width="50">
        </el-table-column>
        <el-table-column
          prop="urlPath"
          label="映射路径"
          width="160">
        </el-table-column>
        <el-table-column
          prop="sort"
          label="排序"
          width="50">
        </el-table-column>
        <el-table-column
          prop="iconName"
          label="图标名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="sId"
          label="组件ID"
          width="60">
        </el-table-column>
        <el-table-column
          prop="path"
          label="组件路径"
          width="80">
        </el-table-column>
        <el-table-column
          prop="componentName"
          label="组件名称"
          width="120">
        </el-table-column>
        <el-table-column
          prop="statusVo.name"
          label="状态"
          width="120">
        </el-table-column>
        <el-table-column
          prop="description"
          label="描述"
          width="120">
        </el-table-column>
        <el-table-column label="操作" width="180">
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

    <el-dialog :title="functionInfoOpTitle" :visible.sync="functionInfoFormVisible" width="800px">
      <el-form :model="currentFunction">
        <el-row>
          <el-col :span="8">
            <el-form-item label="编号" :label-width="formLabelWidth">
              <el-input v-model="currentFunction.funcCode" autocomplete="off" style="width: 150px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="名称" :label-width="formLabelWidth">
              <el-input v-model="currentFunction.funcName" autocomplete="off" style="width: 150px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="类型" :label-width="formLabelWidth">
              <el-input v-model="currentFunction.type" autocomplete="off" style="width: 150px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8"><el-form-item label="映射路径" :label-width="formLabelWidth">
            <el-input v-model="currentFunction.urlPath" autocomplete="off" style="width: 150px"></el-input>
          </el-form-item></el-col>
          <el-col :span="8"><el-form-item label="排序" :label-width="formLabelWidth">
            <el-input v-model="currentFunction.sort" autocomplete="off" style="width: 150px"></el-input>
          </el-form-item></el-col>
          <el-col :span="8"><el-form-item label="图标名称" :label-width="formLabelWidth">
            <el-input v-model="currentFunction.iconName" autocomplete="off" style="width: 150px"></el-input>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="8"><el-form-item label="组件ID" :label-width="formLabelWidth">
            <el-input v-model="currentFunction.sId" autocomplete="off" style="width: 150px"></el-input>
          </el-form-item></el-col>
          <el-col :span="8"><el-form-item label="组件路径" :label-width="formLabelWidth">
            <el-input v-model="currentFunction.path" autocomplete="off" style="width: 150px"></el-input>
          </el-form-item></el-col>
          <el-col :span="8"><el-form-item label="组件名称" :label-width="formLabelWidth">
            <el-input v-model="currentFunction.componentName" autocomplete="off" style="width: 150px"></el-input>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="24"><el-form-item label="状态" :label-width="formLabelWidth">
            <el-radio-group v-model="currentFunction.status" size="mini">
              <el-radio :label="1" border>启用</el-radio>
              <el-radio :label="2" border>禁用</el-radio>
            </el-radio-group>
          </el-form-item></el-col>
        </el-row>
        <el-row>
          <el-col :span="24"><el-form-item label="描述" :label-width="formLabelWidth">
            <el-input type="textarea" :rows="2" v-model="currentFunction.description" autocomplete="off" style="width: 95%"></el-input>
          </el-form-item></el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="functionInfoFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleFunctionInfoOp">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import Vue from "vue";
  export default {
    name: "functionList.vue",
    data() {
      return {
        functionInfoOpType: '',
        functionInfoOpTitle: '',
        functionInfoFormVisible: false,
        functionData:[],
        loading: true,
        currentPage: 1,
        currentPageSize: 10,
        currentTotal: 0,
        formLabelWidth: '70px',
        multipleSelection: [],
        currentOpFunctionId: '',
        currentFunction: {},
        tableHeight: 250
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
      handleSizeChange(val) {
        this.currentPageSize = val;
        this.initData();
      },
      handleCurrentChange(val) {
        this.currentPage = val;
        this.initData();
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      initData() {
        this.loading = true;
        var _this = this;
        var getMainDataUrl = "/system/function/listShow?page=" + this.currentPage + "&pageSize=" + this.currentPageSize;
        this.$ajax.get(getMainDataUrl)
          .then(function (response) {
            _this.functionData = response.data.data;
            _this.currentTotal = response.data.count;
          })
          .catch(function (error) {
            console.log(error);
          });
        this.loading = false;
      },
      handleFunctionInfoOp() {
        if (this.functionInfoOpType == "add") {
          this.handleInsert();
        } else if (this.functionInfoOpType == "update") {
          this.handleUpdate();
        }
      },
      handleAdd() {
        this.functionInfoFormVisible = true;
        this.currentFunction = {};
        this.functionInfoOpType = "add";
        this.functionInfoOpTitle = "添加功能";
      },
      handleEdit(index, row) {
        this.functionInfoFormVisible = true;
        //this.currentUser = row;
        //this.currentUser = Object.assign({}, row);  //对象进行浅复制(只复制属性和值) 这个处理对于嵌套的对象是不起作用的
        this.currentFunction = JSON.parse(JSON.stringify(row));  //对象进行浅复制(只复制属性和值)
        this.functionInfoOpType = "update";
        this.functionInfoOpTitle = "编辑功能信息";
      },
      handleDelete(index, row) {
        this.$confirm('删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var that = this;
          var deleteDataUrl = "/system/function/delete?functionId=" + row.id;
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
              var deleteDataUrl = "/system/function/delete?functionId=" + item.id;
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
        var updateDataUrl = "/system/function/update";
        //添加更新信息
        this.currentFunction.opUserId = Vue.prototype.CurrentUser.id;
        this.currentFunction.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(updateDataUrl, this.currentFunction)
          .then(function (response) {
            console.log(response.data);
            if (response.data.code == "200") {
              _this.$message({
                message: '数据更新成功!',
                type: 'success'
              });
              _this.functionInfoFormVisible = false;
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
        var insertDataUrl = "/system/function/insert";
        this.currentFunction.opUserId = Vue.prototype.CurrentUser.id;
        this.currentFunction.isDel = 0
        this.currentFunction.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(insertDataUrl, this.currentRole)
          .then(function (response) {
            if (response.data.code == "200") {
              _this.$message({
                message: '数据新增成功!',
                type: 'success'
              });
              _this.functionInfoFormVisible = false;
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
