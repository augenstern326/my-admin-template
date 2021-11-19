<template>
  <a-button type="primary" style="margin-bottom: 16px" @click="toggleCollapsed">
    <a-icon :type="collapsed ? 'menu-unfold' : 'menu-fold'" />
  </a-button>
</template>

<script>
    export default {
        name: "Header"
    }
</script>

<style lang="less" scoped>
  .header{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    background: #2c3e50;
  }
</style>
