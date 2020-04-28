<template>
  <div class="navMenu">
      <label v-for="navMenu in navMenus" :key="navMenu.id">
        <el-menu-item v-if="navMenu.subFunction.length==0" :key="navMenu.id" :data="navMenu" :index="navMenu.id" @click="goto(navMenu)">
          <i :class="navMenu.iconName"></i>
          <span slot="title" style="margin-left: 11px" class="el-menu-item-font">{{ navMenu.funcName }}</span>
        </el-menu-item>
        <el-submenu v-else :key="navMenu.id" :data="navMenu" :index="navMenu.id">
          <span slot="title">
            <i :class="navMenu.iconName"></i>
            <span style="margin-left: 11px;" v-show="!collapse" class="el-menu-item-font">{{ navMenu.funcName }}</span>
          </span>
          <nav-Menu :navMenus="navMenu.subFunction" @subClick="goto"></nav-Menu>
        </el-submenu>
      </label>
  </div>
</template>

<script>
    export default {
        name: "navMenu",
        props: ['navMenus','collapse'],
        data() {
            return {
                //
            }
        },
        methods: {
            goto: function (menu) {
                this.$emit('subClick',menu);
            }
        }
    }
</script>

<style>

  .el-menu-item-font {
    font-size: 9pt;

  }
  .el-menu-item {
    height: 35px;
    line-height: 35px;
  }
   {
    height: 35px;
    line-height: 35px;
  }
  .el-menu-item, .el-submenu__title {
    height: 35px !important;
    line-height: 35px !important;
  }
  .el-submenu__icon-arrow {
    top:58%
  }
</style>
