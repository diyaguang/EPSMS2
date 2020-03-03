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
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
                <el-breadcrumb-item>活动列表</el-breadcrumb-item>
                <el-breadcrumb-item>活动详情</el-breadcrumb-item>
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
                    @click="clickIndexTag">{{ this.indexTag.name }}
            </el-tag>
            <el-tag class="tagItem"
                    style="cursor: pointer"
                    v-for="tag in tags"
                    :key="tag.name"
                    closable
                    size="medium"
                    :effect="tag.effect"
                    @close="removeTag(tag)"
                    @click="clickTag(tag,true)"
                    :type="tag.type">
              {{tag.name}}
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
                keepAliveComponents:[],
                isCollapse: false,
                currentTagId: '0',
                indexTag: {id: '0', name: '首页', type: '', url: '/index', effect: 'dark', isKeepAlive: false,componentName:'index.vue'},
                tags: [
                    //{id: '1', name: '标签一', type: 'info', url: '/item1', effect: 'light'},
                ],
                menuData: [
                    {
                        id: '1',
                        name: '系统管理',
                        index: '1',
                        type: 'sub',
                        url: '/admin',
                        icoName: 'el-icon-menu',
                        componentName:'index.vue',
                        childs: [
                            {
                                id: '1-1',
                                name: '用户管理',
                                index: '1-1',
                                type: 'item',
                                url: '/admin/user/list',
                                icoName: 'el-icon-s-custom',
                                componentName:'userList.vue'
                            }
                        ]
                    },
                    {id: '5', name: '菜单5', index: '5', type: 'item', url: '/item5', icoName: 'el-icon-menu',isKeepAlive:false}
                ]
            }
        },
        mounted() {

        },
        watch: {},
        methods: {
            clickChildMenuItem: function (menuItem) {
                let newTag = {
                    'id': menuItem.id,
                    'name': menuItem.name,
                    'type': 'info',
                    'url': menuItem.url,
                    'effect': 'light',
                    'componentName':menuItem.componentName
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
                if(this.keepAliveComponents.indexOf(this.tags[opIndex].componentName)<0){
                  this.keepAliveComponents.push(this.tags[opIndex].componentName);
                }
            },
            removeTag: function (tag) {
                if (tag.id === this.currentTagId) {
                    var opIndex = this.tags.indexOf(tag);
                    this.keepAliveComponents.splice(this.keepAliveComponents.indexOf(tag.componentName),1);
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
                    this.$router.push(tag.url);
                }
            },
            clickIndexTag: function () {
                this.tags.forEach((item, index, array) => {
                    item.type = 'info';
                    item.effect = 'light'
                });
                //index tag 的变化
                this.indexTag.type = '';
                this.indexTag.effect = 'dark';
                this.currentTagId = this.indexTag.id;
                //跳转
                this.$router.push(this.indexTag.url);
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

