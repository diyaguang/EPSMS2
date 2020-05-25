<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix" style="line-height: 39px;">
        <span>用户列表</span>
        <el-button-group style="float: right; padding: 5px 0; margin-right: 10px">
          <el-button type="primary" icon="el-icon-document-add" @click="handleAdd"></el-button>
          <el-button type="primary" icon="el-icon-document-delete" @click="handleBatchDelete"></el-button>
          <el-button type="primary" icon="el-icon-document-copy"></el-button>
          <el-button type="primary" icon="el-icon-printer"></el-button>
        </el-button-group>
      </div>
      <el-table
        ref="multipleTable"
        @selection-change="handleSelectionChange"
        v-loading="loading"
        :data="userData"
        style="width: 100%"
        :height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="code"
          label="编号"
          width="120">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="120">
        </el-table-column>
        <el-table-column
          prop="password"
          label="密码"
          width="120">
        </el-table-column>
        <el-table-column
          prop="description"
          label="描述"
          width="120">
        </el-table-column>
        <el-table-column
          prop="sort"
          label="排序"
          width="120">
        </el-table-column>
        <el-table-column
          prop="statusVo.name"
          label="状态"
          width="120">
        </el-table-column>
        <el-table-column label="角色" width="120">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleRoleEdit(scope.$index, scope.row)">配置
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
    <el-dialog :title="userInfoOpTitle" :visible.sync="userInfoFormVisible" width="850px">
      <el-form :model="currentUser">
        <el-row>
          <el-col :span="8">
            <el-form-item label="用户编号" :label-width="formLabelWidth">
              <el-input v-model="currentUser.code" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="名称" :label-width="formLabelWidth">
              <el-input v-model="currentUser.name" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="密码" :label-width="formLabelWidth">
              <el-input v-model="currentUser.password" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="年龄" :label-width="formLabelWidth">
              <el-input v-model="currentUser.age" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="生日" :label-width="formLabelWidth">
              <el-input v-model="currentUser.birthday" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="currentUser.phone" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="身份证" :label-width="formLabelWidth">
              <el-input v-model="currentUser.iDCard" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="职位" :label-width="formLabelWidth">
              <el-input v-model="currentUser.position" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="邮箱" :label-width="formLabelWidth">
              <el-input v-model="currentUser.email" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-input v-model="currentUser.userName" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="邮政编码" :label-width="formLabelWidth">
              <el-input v-model="currentUser.PostCode" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="部门" :label-width="formLabelWidth">
              <el-input v-model="currentUser.department" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="排序" :label-width="formLabelWidth">
              <el-input v-model="currentUser.sort" autocomplete="off" style="width: 100px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="状态" :label-width="formLabelWidth">
              <el-radio-group v-model="currentUser.status" size="mini">
                <el-radio :label="101" border>启用</el-radio>
                <el-radio :label="102" border>禁用</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="联系地址" :label-width="formLabelWidth">
              <el-input v-model="currentUser.address" autocomplete="off" size="9"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="描述" :label-width="formLabelWidth">
              <el-input type="textarea" :rows="2" v-model="currentUser.description" autocomplete="off" size="9"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userInfoFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleUserInfoOp">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="用户角色配置" :visible.sync="userRoleFormVisible" width="750px">
      <el-transfer v-model="currentUserRoles" :data="baseRoles"
                   :titles="['可选角色','已选角色']"
                   :button-texts="['到左边', '到右边']">
      </el-transfer>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userRoleFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleRoleUpdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import Vue from 'vue';

  export default {
    name: "userList.vue",
    data() {
      return {
        userInfoOpType: '',
        userInfoOpTitle: '',
        userInfoFormVisible: false,
        userRoleFormVisible: false,
        loading: true,
        currentPage: 1,
        currentPageSize: 10,
        currentTotal: 0,
        userData: [],
        currentUser: {},
        baseRoles: [],
        currentUserRoles: [],
        currentOpUserId: '',
        formLabelWidth: '100px',
        multipleSelection: [],
        tableHeight:250,
        dictDepartment:[],
        dictPosition:[]
      }
    },
    created: function () {
      this.initData();
    },
    mounted:function(){
      setTimeout(() => {
        this.tableHeight = window.innerHeight - this.$refs.multipleTable.$el.offsetTop-150;
      },100)
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
      initData() {
        this.loading = true;
        var _this = this;
        var getMainDataUrl = "/user/listShow?page=" + this.currentPage + "&pageSize=" + this.currentPageSize;
        this.$ajax.get(getMainDataUrl)
          .then(function (response) {
            _this.userData = response.data.data;
            console.log(response.data.data);
            _this.currentTotal = response.data.count;
          })
          .catch(function (error) {
            console.log(error);
          });
        this.loading = false;
      },
      handleUserInfoOp() {
        if (this.userInfoOpType == "add") {
          this.handleInsert();
        } else if (this.userInfoOpType == "update") {
          this.handleUpdate();
        }
      },
      handleRoleUpdate(row) {
        if (this.currentUserRoles.length == 0) {
          this.$confirm('已选角色为空, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.updateRole();
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消保存'
            });
          });
        } else {
          this.updateRole();
        }
      },
      updateRole(){
        var that = this;
        var saveRoleUrl = "/user/userRoleLink/modify?userId=" + that.currentOpUserId;
        var data = {
          roleIds: that.currentUserRoles
        };
        this.$ajax.post(saveRoleUrl, that.currentUserRoles)
          .then(function (response) {
            if (response.data.code == 200) {
              that.$message({
                message: '角色保存成功!',
                type: 'success'
              });
              that.initData();
              that.userRoleFormVisible = false;
            } else {
              that.$message({
                message: '角色保存失败!' + response.data.msg,
                type: 'error'
              });
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleRoleEdit(index, row) {
        //获取角色数据
        var _this = this;
        var getRoleDataUrl = "/system/role/listForOp";
        this.$ajax.get(getRoleDataUrl)
          .then(function (response) {
            _this.baseRoles = response.data;
            //获取完成后，更新状态
            _this.userRoleFormVisible = true;
            //获取当前用户的角色列表，循环key 为 数组，表示已选
            _this.currentOpUserId = row.id;
            _this.currentUserRoles = [];
            row.roles.forEach((item, index, array) => {
              _this.currentUserRoles.push(item.id);
            });
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleAdd() {
        this.userInfoFormVisible = true;
        this.currentUser = {};
        this.userInfoOpType = "add";
        this.userInfoOpTitle = "添加用户";
      },
      handleEdit(index, row) {
        this.userInfoFormVisible = true;
        //this.currentUser = row;
        //this.currentUser = Object.assign({}, row);  //对象进行浅复制(只复制属性和值) 这个处理对于嵌套的对象是不起作用的
        //this.currentUser = JSON.parse(JSON.stringify(row));  //对象进行浅复制(只复制属性和值)
        //从数组中根据条件查找对象
        this.currentUser = this.userData.find(function (x) {
          return x.id=row.id;
        });


        this.userInfoOpType = "update";
        this.userInfoOpTitle = "编辑用户信息";
      },
      handleDelete(index, row) {
        this.$confirm('删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var that = this;
          var deleteDataUrl = "/user/delete?userId=" + row.id;
          this.$ajax.get(deleteDataUrl)
            .then(function (response) {
              if (response.data.code == 200) {
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
              var deleteDataUrl = "/user/delete?userId=" + item.id;
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
        var updateDataUrl = "/user/update";
        //添加更新信息
        this.currentUser.opUserId = Vue.prototype.CurrentUser.id;
        this.currentUser.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(updateDataUrl, this.currentUser)
          .then(function (response) {
            console.log(response.data);
            if (response.data.code == 200) {
              _this.$message({
                message: '数据更新成功!',
                type: 'success'
              });
              _this.userInfoFormVisible = false;
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
        var insertDataUrl = "/user/insert";
        this.currentUser.opUserId = Vue.prototype.CurrentUser.id;
        this.currentUser.isDel = 0
        this.currentUser.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(insertDataUrl, this.currentUser)
          .then(function (response) {
            if (response.data.code == 200) {
              _this.$message({
                message: '数据新增成功!',
                type: 'success'
              });
              _this.userInfoFormVisible = false;
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
</style>
