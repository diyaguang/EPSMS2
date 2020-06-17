<template>
  <div>
    index
    <el-button @click="testGraphql">test Graphql</el-button>
    {{this.userList}}
  </div>
</template>

<script>
  import gql from "graphql-tag";
  const getUser = gql`query findAllUser{
  findAllUser{
    id
    name
    phone
    }
  }`

  export default {
    name: "index.vue",
    data(){
      return {
        userList:[],
        loading:0
      }
    },
    methods:{
      testGraphql(){
        console.log(this.$apollo.query)
        this.$apollo.query({
          query:getUser,
          variables:{
          }
        }).then(res=>{
          //this.parse(res.data.findAllUser)
          console.log(res.data.findAllUser)
          this.userList = res.data.findAllUser;
        })
        .catch(err=>{
          console.log(err)
        })
      }
    }
  }
</script>

<style scoped>

</style>
