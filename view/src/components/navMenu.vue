<template>
  <div class="navMenu">
      <label v-for="navMenu in navMenus" :key="navMenu.id">
        <el-menu-item v-if="navMenu.childs==null" :key="navMenu.id" :data="navMenu" :index="navMenu.index" @click="goto(navMenu)">
          <i :class="navMenu.icoName"></i>
          <span slot="title" style="margin-left: 11px">{{ navMenu.name }}</span>
        </el-menu-item>
        <el-submenu v-if="navMenu.childs" :key="navMenu.id" :data="navMenu" :index="navMenu.index">
          <span slot="title">
            <i :class="navMenu.icoName"></i>
            <span style="margin-left: 11px;" v-show="!collapse">{{ navMenu.name }}</span>
          </span>
          <nav-Menu :navMenus="navMenu.childs" @subClick="goto"></nav-Menu>
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

<style scoped>

</style>
