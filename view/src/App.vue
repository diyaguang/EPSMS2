<template>
    <div id="app">
        <el-container  style="height: 100%">
            <el-header class="showHeader">
              <i class="el-icon-s-grid" style="font-size: 24px" @click="isCollapse=!isCollapse"></i>
            </el-header>
            <el-container direction="horizontal">
                <el-aside width="66">
                    <el-menu  class="el-menu-vertical-demo" @open="handleOpen"
                             @close="handleClose"
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
                <el-main>Main</el-main>
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
                isCollapse: true
            }
        },
        mounted() {

        },
        watch: {},
        methods: {
            handleOpen() {

            },
            handleClose() {

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

    .showHeader {
        position: fixed;
        top: 0;
        left: 0px;
        width: 100%;
      line-height: 60px;
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
        line-height: 160px;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200px;
        min-height: 400px;
    }
</style>
