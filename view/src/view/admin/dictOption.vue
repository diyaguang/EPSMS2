<template>
  <div>
    <el-card class="box-card">
      <div slot="header"  style="line-height: 39px;">
        <span>字典列表</span>
      </div>
      <div style=" padding: 5px 0; margin-left: 50px">
        <el-form :inline="true" :model="dictBase" size="mini" >
          <el-form-item label="名称">
            <el-input v-model="dictBase.label" placeholder="项目名称" style="width: 150px"></el-input>
          </el-form-item>
          <el-form-item label="数值">
            <el-input v-model="dictBase.value" placeholder="项目数值" style="width: 150px"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-circle-plus-outline" @click="handleAdd">下级新增</el-button>
            <el-button type="primary" icon="el-icon-edit" @click="handleUpdate">更新(名称)</el-button>
            <el-button type="primary" icon="el-icon-delete" @click="handleDel">删除</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div id="div">
        <el-tree
          ref="tree"
          :data="dictData"
          :default-expanded-keys="defaultExpandedKeys"
          accordion
          :default-expand-all="false"
          @node-click="handleNodeClick"
          node-key="key">
        </el-tree>
      </div>
      <div class="bottom clearfix">
        <span style="font-size: 9pt; line-height: 20px">
        说明：<br>
        1.新增下级时，需要在 Tree控件上选择需要操作的 父级项，编辑名称和数值后，点击【下级新增】按钮。<br>
        2.更新元素，只能操作 名称修改，其他数值不能够修改，如需调整顺序，请删除原有项后，再新增信息项。
          </span>
      </div>
    </el-card>
  </div>
</template>

<script>
  import Vue from 'vue';

  export default {
    name: "dictOption.vue",
    data() {
      return {
        dictData:[],
        dictBase:{
          key:'',
          label:'',
          value:'',
          parentId:''
        },
        defaultExpandedKeys:[]
      }
    },
    created: function () {
      this.initData();
    },
    mounted: function () {

    },
    methods: {
      handleAdd(){
        var _this = this;
        var insertDataUrl = "/system/dictInfo/insert";
        this.$ajax.post(insertDataUrl, this.dictBase)
          .then(function (response) {
            if (response.data.code == "200") {
              _this.$message({
                message: '数据新增成功!',
                type: 'success'
              });
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
      },
      handleUpdate(){
        var _this = this;
        var updateDataUrl = "/system/dictInfo/update";
        /*this.dictBase.opUserId = Vue.prototype.CurrentUser.id;
        this.currentRole.isDel = 0
        this.currentRole.companyId = Vue.prototype.CurrentUser.companyId;*/
        this.$ajax.post(updateDataUrl, this.dictBase)
          .then(function (response) {
            if (response.data.code == "200") {
              _this.$message({
                message: '数据更新成功!',
                type: 'success'
              });
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
      handleDel(){
        var _this = this;
        var deleteDataUrl = "/system/dictInfo/delete?dictId=" + this.dictBase.key;
        /*this.dictBase.opUserId = Vue.prototype.CurrentUser.id;
        this.currentRole.isDel = 0
        this.currentRole.companyId = Vue.prototype.CurrentUser.companyId;*/
        this.$ajax.get(deleteDataUrl)
          .then(function (response) {
            if (response.data.code == "200") {
              _this.$message({
                message: '数据删除成功!',
                type: 'success'
              });
              _this.initData();
            } else {
              _this.$message({
                message: '数据删除失败!' + response.data.msg,
                type: 'error'
              });
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      handleNodeClick(data){
        this.dictBase.key = data.key;
        this.dictBase.label=data.label;
        this.dictBase.value = data.value;
        this.dictBase.parentId = data.parentId;
        this.defaultExpandedKeys = [];
        //设置树空间默认打开状态，如果有 parent，则选择，否则选择 自身节点
        if(data.parentId==null || data.parentId == undefined){
          this.defaultExpandedKeys.push(data.key);
        }else{
          this.defaultExpandedKeys.push(data.parentId);
        }

      },
      initData() {
        this.loading = true;
        var _this = this;
        var getMainDataUrl = "/system/dict/dictForTop";
        this.$ajax.get(getMainDataUrl)
          .then(function (response) {
            _this.dictData = response.data;
          })
          .catch(function (error) {
            console.log(error);
          });
        this.loading = false;
      },
    },
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
  .bottom {
    border-top: solid 1px #D3DCE6;
    margin-top: 13px;
    line-height: 12px;
    padding-top: 5px;
  }
</style>
