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
            <el-button type="primary" icon="el-icon-circle-plus-outline" @click="handleAdd">添加</el-button>
            <el-button type="primary" icon="el-icon-edit" @click="handleUpdate">更新</el-button>
            <el-button type="primary" icon="el-icon-delete" @click="handleDel">删除</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div id="div">
        <el-tree
          ref="tree"
          :data="dictData"
          accordion
          :default-expand-all="false"
          @node-click="handleNodeClick"
          node-key="key">
        </el-tree>
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
          value:''
        }
      }
    },
    created: function () {
      this.initData();
    },
    mounted: function () {

    },
    methods: {
      handleAdd(){

      },
      handleUpdate(){

      },
      handleDel(){
        
      },
      handleNodeClick(data){
        this.dictBase.key = data.key;
        this.dictBase.label=data.label;
        this.dictBase.value = data.value;
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
</style>
