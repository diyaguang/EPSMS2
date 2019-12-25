<template>
    <div id="app">
        <el-container style="height: 100%">
            <el-header class="showHeader">
                <div style="position: absolute; left: 66px; top: 5px">
                    <el-tooltip class="item" effect="dark" content="缩进工具栏" placement="top-start">
                        <i class="el-icon-s-grid" style="font-size: 18px" @click="isCollapse=!isCollapse"></i>
                    </el-tooltip>
                </div>
                <div style="position: absolute;right: 10px;top: 0px">
                    <div class="fr" style="margin-top: 23px;margin-left: 20px">
                        <i class="el-icon-warning-outline" style="font-size: 17px"></i>
                    </div>
                    <div class="fr" style="margin-top: 23px;">
                        <i class="el-icon-set-up" style="font-size: 17px"></i>
                    </div>
                    <div class="fr" style="margin-top: 8px;margin-left: 20px">
                        <el-badge is-dot class="item">
                            <i class="el-icon-bell" style="font-size: 17px;"></i>
                        </el-badge>
                    </div>
                    <div class="fr" style="margin-top: 21px;margin-left: 20px">
                        <el-dropdown size="medium">
                            <span class="el-dropdown-link">
                                <i class="el-icon-s-custom el-icon--left"></i>用户切换
                            </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item>aaaa</el-dropdown-item>
                                <el-dropdown-item>bbbb</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </div>
            </el-header>
            <el-container direction="horizontal">
                <el-aside width="66">
                    <el-menu class="el-menu-vertical-demo"
                             :collapse="isCollapse" style="margin-top: 60px">
                        <el-submenu index="1">
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
                <el-main>
                    Main
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

    export default {
        name: 'app',
        components: {},
        data: function () {
            return {
                isCollapse: true,
                activeIndex: '1'
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

    .showHeader {
        position: fixed;
        top: 0;
        left: 0px;
        width: 100%;
    }

    .showFooter {
        position: fixed;
        bottom: 0;
        left: 0px;
        width: 100%;
        height: 30px;
    }

    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        line-height: 200px;
    }

    .el-main {
        background-color: #E9EEF3;
        color: #333;

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
