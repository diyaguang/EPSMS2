<template>
  <div id="app">
    <el-container style="height: 100%">
      <el-aside width="50" style="background-color: #545c64">
        <el-menu class="el-menu-vertical-demo"
                 background-color="#545c64"
                 text-color="#fff"
                 :collapse="isCollapse" style="margin-top: 60px; border: 0px">
          <nav-menu :navMenus="menuData" :collapse="isCollapse" @subClick="clickChildMenuItem"></nav-menu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header class="showHeader" style="height: 87px">
          <div class="showHeaderUp">
            <div style="position: absolute; left: 10px;">
              <el-tooltip class="item" effect="dark" content="缩进工具栏" placement="top-start">
                <i :class="collapseClass" style="font-size: 18px" @click="isCollapse=!isCollapse"></i>
              </el-tooltip>
            </div>
            <div style="position: absolute; left: 60px; top: 18px">
              <el-breadcrumb separator="/">
                <el-breadcrumb-item v-for="breadcrumbPath in breadcrumbPathItem" :key="breadcrumbPath.sId"><a
                  :href="breadcrumbPath.urlPath">{{breadcrumbPath.funcName}}</a></el-breadcrumb-item>
              </el-breadcrumb>
            </div>
            <div style="position: absolute; right: 50px;">
              <div class="fr" style="margin-top: 8px;margin-left: 20px;">
                <el-popover
                  placement="bottom"
                  width="100"
                  visible-arrow="false"
                  trigger="click">

                  <img slot="reference" class="headerPhoto"
                       style="width: 30px;height: 30px;background-color: #42b983;">
                </el-popover>
              </div>
              <div class="fr" style="margin-top: 15px;margin-left: 20px">
                <i class="el-icon-warning-outline" style="font-size: 17px"></i>
              </div>
              <div class="fr" style="margin-top: 15px;">
                <i class="el-icon-set-up" style="font-size: 17px"></i>
              </div>
              <div class="fr" style="margin-top: 0px;margin-left: 20px">
                <el-badge is-dot class="item">
                  <i class="el-icon-bell" style="font-size: 17px;"></i>
                </el-badge>
              </div>
            </div>
          </div>

          <div style="position: absolute; left: 10px; top: 55px;">
            <el-tag class="tagItem"
                    size="medium"
                    style="cursor: pointer"
                    :type="indexTag.type"
                    :effect="indexTag.effect"
                    @click="clickIndexTag">{{ this.indexTag.funcName }}
            </el-tag>
            <el-tag class="tagItem"
                    style="cursor: pointer"
                    v-for="tag in tags"
                    :key="tag.funcName"
                    closable
                    size="medium"
                    :effect="tag.effect"
                    @close="removeTag(tag)"
                    @click="clickTag(tag,true)"
                    :type="tag.type">
              {{tag.funcName}}
            </el-tag>
          </div>
        </el-header>
        <el-main class="el-main">
          <keep-alive :include="keepAliveComponents">
            <router-view></router-view>
          </keep-alive>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import Vue from 'vue';
  import navMenu from "./components/navMenu";

  export default {
    name: 'app',
    components: {navMenu},
    computed: {
      collapseClass: function () {
        if (this.isCollapse) {
          return 'el-icon-s-unfold'
        } else {
          return 'el-icon-s-fold';
        }
      }
    },
    data: function () {
      return {
        keepAliveComponents: [],
        isCollapse: false,
        currentTagId: '0',
        indexTag: {
          id: '0',
          funcName: '首页',
          type: '',
          urlPath: '/index',
          effect: 'dark',
          isKeepAlive: false,
          componentName: 'index.vue'
        },
        tags: [
          //{id: '1', name: '标签一', type: 'info', url: '/item1', effect: 'light'},
        ],
        breadcrumbPathItemTemp: {id: 0, funcName: '首页', urlPath: '/index'},
        breadcrumbPathItem: [{id: 0, funcName: '首页', urlPath: '/index'}],
        tmpMenuData: [],
        menuData:[]
      }

    },
    mounted() {
      this.getCurrentUserInfo();
      this.getMenuData();
    },
    watch: {},
    methods: {
      getCurrentUserInfo:function(){
        var _this = this;
        var getCurrentUserDataUrl = "/user/get";
        var data = {userName:'diyaguang',password:'96777'};
        this.$ajax.post(getCurrentUserDataUrl,data)
          .then(function (response) {
            Vue.prototype.CurrentUser = response.data;
            //_this.currentUser = response.data;
            console.log(Vue.prototype.CurrentUser);
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      //获取菜单menu的方法
      getMenuData: function () {
        var _this = this;
        var getMenuDataUrl = "/user/userMenu?userId=7f9c095ee0ed43ba85d6f6b3d533AAAc";
        this.$ajax.get(getMenuDataUrl)
          .then(function (response) {
            _this.menuData = response.data.data;
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      //计算面包屑导航栏的函数
      getBreadcrumbPath: function (menuData, currentMenu) {
        this.breadcrumbPathItem = [];
        this.breadcrumbPathItem.push(this.breadcrumbPathItemTemp);
        let ids = currentMenu.path.split("-");
        var currentItem = menuData;
        ids.forEach(id => {
          for (var i = 0; i < currentItem.length; i++) {
            if (currentItem[i].sId == id) {
              let newPath = {id: currentItem[i].id, funcName: currentItem[i].funcName, urlPath: currentItem[i].urlPath};
              this.breadcrumbPathItem.push(newPath);
              currentItem = currentItem[i].subFunction;
              break;
            }
          }
        });
      },
      //左侧菜单选择后触发的函数
      clickChildMenuItem: function (menuItem) {
        let newTag = {
          'id': menuItem.id,
          'funcName': menuItem.funcName,
          'path': menuItem.path,
          'type': 'info',
          'urlPath': menuItem.urlPath,
          'effect': 'light',
          'componentName': menuItem.componentName
        };
        var isAdd = true;
        var opIndex = 0;
        this.tags.forEach((item, index, array) => {
          if (item.id === menuItem.id) {
            isAdd = false;
            opIndex = index;
          }
        });
        if (isAdd) {
          this.tags.push(newTag);
          opIndex = this.tags.length - 1;
        }

        this.clickTag(this.tags[opIndex]);
        if (this.keepAliveComponents.indexOf(this.tags[opIndex].componentName) < 0) {
          this.keepAliveComponents.push(this.tags[opIndex].componentName);
        }
      },
      //删除Tag标签的函数
      removeTag: function (tag) {
        if (tag.id === this.currentTagId) {
          var opIndex = this.tags.indexOf(tag);
          this.keepAliveComponents.splice(this.keepAliveComponents.indexOf(tag.componentName), 1);
          this.tags.splice(opIndex, 1);
          //默认这个元素之后的tag被打开
          var tag = this.tags[opIndex];
          if (tag) {
            this.clickTag(tag);
          } else {
            tag = this.tags[opIndex - 1];
            if (tag)
              this.clickTag(tag);
            else
              this.clickIndexTag();
          }
        } else {
          this.tags.splice(this.tags.indexOf(tag), 1);
        }

      },
      //单击 tag 标签的函数
      clickTag: function (tag) {
        if (tag) {
          this.currentTagId = tag.id;
          this.tags.forEach((item, index, array) => {
            item.type = 'info';
            item.effect = 'light'
          });
          //index tag 的变化
          this.indexTag.type = 'info';
          this.indexTag.effect = 'light';

          //tag 的变化
          tag.type = '';
          tag.effect = 'dark';
          //跳转

          this.$router.push(tag.urlPath);
          this.getBreadcrumbPath(this.menuData, tag);
        }
      },
      //单击 index 首页的 tag 触发的函数
      clickIndexTag: function () {
        this.tags.forEach((item, index, array) => {
          item.type = 'info';
          item.effect = 'light'
        });
        //index tag 的变化
        this.indexTag.type = '';
        this.indexTag.effect = 'dark';
        this.currentTagId = this.indexTag.id;
        //面包屑地址栏的变化
        this.breadcrumbPathItem = [];
        this.breadcrumbPathItem.push(this.breadcrumbPathItemTemp);
        //跳转
        this.$router.push(this.indexTag.urlPath);
      }
    }
  }
</script>

<style>
  #app {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }

  .headerPhoto {
    border-radius: 10px;
    border: 0px;
  }

  .tagItem {
    margin-left: 5px;
  }

  .showHeader {
    position: relative;
    width: 100%;
    height: 90px;
  }

  .showHeaderUp {
    height: 45px;
    -webkit-box-shadow: 0 1px 4px rgba(0, 21, 41, .08);
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);
  }

  .showFooter {
    position: fixed;
    bottom: 0;
    left: 0px;
    width: 100%;
    height: 30px;
  }

  .el-header, .el-footer {
    background-color: #fff;
    color: #333;
    padding: 0px 0px !important;
    border-bottom: 1px solid #d3d7d4;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    line-height: 200px;
  }

  .el-main {
    position: relative;
    background-color: #fff;
    color: #333;
    padding: 10px;
  }

  .el-menu {
    background-color: #0a2128;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }

  .item {
    margin-top: 15px;
    margin-right: 20px;
  }

  .fl {
    float: left;
  }

  .fr {
    float: right;
  }
</style>

