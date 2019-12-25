<template>
    <div id="app">
        <el-container style="height: 100%">
            <el-aside width="50" style="background-color: #545c64">
                <el-menu class="el-menu-vertical-demo"
                         background-color="#545c64"
                         text-color="#fff"
                         :collapse="isCollapse" style="margin-top: 60px; border: 0px">
                    <el-submenu index="1" background-color="#545c64">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span slot="title">导航一</span>
                        </template>
                        <el-menu-item-group>
                            <span slot="title">分组一</span>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <span slot="title">选项4</span>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-menu-item index="2">
                        <i class="el-icon-menu"></i>
                        <span slot="title">导航二</span>
                    </el-menu-item>
                    <el-menu-item index="3" disabled>
                        <i class="el-icon-document"></i>
                        <span slot="title">导航三</span>
                    </el-menu-item>
                    <el-menu-item index="4">
                        <i class="el-icon-setting"></i>
                        <span slot="title">导航四</span>
                    </el-menu-item>
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
                                v-for="tag in tags"
                                :key="tag.name"
                                closable
                                size="medium"
                                :type="tag.type">
                            {{tag.name}}
                        </el-tag>
                    </div>
                </el-header>
                <el-main class="el-main">
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

    export default {
        name: 'app',
        components: {},
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
                isCollapse: false,
                activeIndex: '1',
                tags: [
                    {name: '标签一', type: ''},
                    {name: '标签二', type: ''},
                    {name: '标签三', type: ''},
                    {name: '标签四', type: ''},
                    {name: '标签五', type: ''}
                ]
            }
        },
        mounted() {

        },
        watch: {},
        methods: {}
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
