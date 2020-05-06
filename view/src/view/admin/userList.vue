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
        height="250">
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
              @click="handleRoleEdit(scope.$index, scope.row)">配置</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
    <el-dialog :title="userInfoOpTitle" :visible.sync="userInfoFormVisible" width="500px">
      <el-form :model="currentUser">
        <el-form-item label="用户编号" :label-width="formLabelWidth">
          <el-input v-model="currentUser.code" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="currentUser.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="currentUser.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input v-model="currentUser.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序" :label-width="formLabelWidth">
          <el-input v-model="currentUser.sort" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userInfoFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleUserInfoOp">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="用户角色配置" :visible.sync="userRoleFormVisible" width="500px">
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
        userInfoOpType:'',
        userInfoOpTitle:'',
        userInfoFormVisible:false,
        userRoleFormVisible:false,
        loading:true,
        currentPage:1,
        currentPageSize:10,
        currentTotal:0,
        userData:[],
        currentUser:{},
        baseRoles:[],
        currentUserRoles:[],
        formLabelWidth: '50px',
        multipleSelection: []
      }
    },
    created: function () {
      this.initData();
    },
    methods:{
      handleSizeChange(val){
        this.currentPageSize = val;
        this.initData();
      },
      handleCurrentChange(val){
        this.currentPage = val;
        this.initData();
      },
      initData(){
        this.loading = true;
        var _this = this;
        var getMainDataUrl = "/user/listShow?page="+this.currentPage+"&pageSize="+this.currentPageSize;
        this.$ajax.get(getMainDataUrl)
          .then(function (response) {
              _this.userData = response.data.data;
              _this.currentTotal = response.data.count;
          })
          .catch(function (error) {
            console.log(error);
          });
        this.loading = false;
      },
      handleUserInfoOp(){
        if(this.userInfoOpType=="add"){
          this.handleInsert();
        }else if(this.userInfoOpType=="update"){
          this.handleUpdate();
        }
      },
      handleRoleUpdate(){
        var _this = this;
        var updateDataUrl = "/user/update";
        //添加更新信息
        this.currentUser.opUserId = Vue.prototype.CurrentUser.id;
        this.currentUser.companyId = Vue.prototype.CurrentUser.companyId;
        var postData = {
          userId:'xxxxxx',
          roles:[]
        };
        this.$ajax.post(updateDataUrl,postData)
          .then(function (response) {
            console.log(response.data);
            if(response.data.code==200){
              _this.$message({
                message: '角色配置成功!',
                type: 'success'
              });
              _this.userInfoFormVisible = false;
              _this.initData();
            }else{
              _this.$message({
                message: '角色配置失败!'+response.data.msg,
                type: 'error'
              });
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleRoleEdit(index, row){
        console.log(row);
        this.userRoleFormVisible = true;
        //从后台获取角色数据列表 循环遍历为 key，label，disabled 数组
        //获取当前用户的角色列表，循环key 为 数组，表示已选
      },
      handleAdd(){
        this.userInfoFormVisible = true;
        this.currentUser = {};
        this.userInfoOpType="add";
        this.userInfoOpTitle = "添加用户";
      },
      handleEdit(index, row) {
        this.userInfoFormVisible = true;
        //this.currentUser = row;
        //this.currentUser = Object.assign({}, row);  //对象进行浅复制(只复制属性和值) 这个处理对于嵌套的对象是不起作用的
        this.currentUser = JSON.parse(JSON.stringify(row));  //对象进行浅复制(只复制属性和值)
        this.userInfoOpType="update";
        this.userInfoOpTitle="编辑用户信息";
      },
      handleDelete(index, row) {
        this.$confirm('删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var that = this;
          var deleteDataUrl = "/user/delete?userId="+row.id;
          this.$ajax.get(deleteDataUrl)
            .then(function (response) {
              if(response.data.code==200) {
                that.$message({
                  message: '数据删除成功!',
                  type: 'success'
                });
                that.initData();
              }else{
                that.$message({
                  message: '数据删除失败!'+response.data.msg,
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
      handleSelectionChange(val){
        this.multipleSelection = val;
      },
      handleBatchDelete(){
        let size = this.multipleSelection.length;
        if(size>0) {
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
        }else{
          this.$message({
            type: 'info',
            message: '请先选择要删除的记录'
          });
        }
      },
      handleUpdate(){
        var _this = this;
        var updateDataUrl = "/user/update";
        //添加更新信息
        this.currentUser.opUserId = Vue.prototype.CurrentUser.id;
        this.currentUser.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(updateDataUrl,this.currentUser)
          .then(function (response) {
            console.log(response.data);
            if(response.data.code==200){
              _this.$message({
                message: '数据更新成功!',
                type: 'success'
              });
              _this.userInfoFormVisible = false;
              _this.initData();
            }else{
              _this.$message({
                message: '数据更新失败!'+response.data.msg,
                type: 'error'
              });
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleInsert(){
        var _this = this;
        var insertDataUrl = "/user/insert";
        this.currentUser.opUserId = Vue.prototype.CurrentUser.id;
        this.currentUser.isDel=0
        this.currentUser.companyId = Vue.prototype.CurrentUser.companyId;
        this.$ajax.post(insertDataUrl,this.currentUser)
          .then(function (response) {
            if(response.data.code==200){
              _this.$message({
                message: '数据新增成功!',
                type: 'success'
              });
              _this.userInfoFormVisible = false;
              _this.initData();
            }else{
              _this.$message({
                message: '数据新增失败!'+response.data.msg,
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
