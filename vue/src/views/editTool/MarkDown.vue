<template>
  <mavon-editor v-model="value" :style="{height:'100%'}"/>
</template>

<script>
    export default {
        name: "MarkDown",
        data(){
          return {
            value:""
          }
        }
    }
</script>

<style scoped>

</style>
