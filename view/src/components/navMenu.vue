<template>
    <div class="navMenu">
      <label v-for="navMenu in navMenus" :key="navMenu.id">
        <el-menu-item v-if="navMenu.childs==null" :key="navMenu.id" :data="navMenu" :index="navMenu.index" @click="handleRoute(navMenu.url)">
          <i :class="navMenu.icoName"></i>
          <span slot="title">{{ navMenu.name }}</span>
        </el-menu-item>

        <el-submenu v-if="navMenu.childs" @click.native="goto(navMenu.url)" :key="navMenu.id" :data="navMenu" :index="navMenu.index">
          <template slot="title">
            <i :class="navMenu.icoName"></i>
            <span>{{ navMenu.name }}</span>
          </template>
          <nav-Menu :navMenus="navMenu.childs"></nav-Menu>
        </el-submenu>
      </label>
    </div>
</template>

<script>
    export default {
        name: "navMenu",
        props:['navMenus'],
        data(){
            return {
                //
            }
        },
        methods:{
            goto:function(menu){
                if(menu){
                    this.$route.push(menu)
                }
            }
        }
    }
</script>

<style scoped>

</style>
