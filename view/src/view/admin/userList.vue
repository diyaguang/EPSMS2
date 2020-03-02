<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix" style="line-height: 39px;">
        <span>用户列表</span>
        <el-button-group style="float: right; padding: 5px 0; margin-right: 10px">
          <el-button type="primary" icon="el-icon-document-add"></el-button>
          <el-button type="primary" icon="el-icon-document-delete"></el-button>
          <el-button type="primary" icon="el-icon-document-copy"></el-button>
          <el-button type="primary" icon="el-icon-printer"></el-button>
        </el-button-group>
      </div>
      <el-table
        :data="userData"
        style="width: 100%"
        height="250">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          fixed
          prop="id"
          label="ID"
          width="350">
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
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="currentTotal">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "userList.vue",
    data() {
      return {
        currentPage:1,
        currentPageSize:10,
        currentTotal:0,
        userData:[]
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
        var _this = this;
        var getMainDataUrl = "/user/list";
        this.$ajax.get(getMainDataUrl)
          .then(function (response) {
              _this.userData = response.data;
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
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
