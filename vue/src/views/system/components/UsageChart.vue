<template>
  <div id="myChart" :style="{width: '100%', height: '300px'}"></div>
</template>

<script>
  export default {
    name: 'UsageChart',
    props:['dateTime','memoryUsage','title'],
    data () {
    },
    mounted(){
      this.drawLine();
    },
    methods: {
      drawLine(){
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        myChart.setOption({
          title: { text: this.title},
          tooltip: {},
          xAxis: {
            data: this.dateTime
          },
          yAxis: {},
          series: [{
            name: '销量',
            type: 'bar',
            data: this.memoryUsage
          }]
        });
      }
    }
  }
</script>

<style scoped>

</style>

