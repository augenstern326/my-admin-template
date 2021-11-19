<template>
  <div class="main">
    <a-table class="systemBaseInfo" :columns="columns" :data-source="SysInfo" :rowKey="(record,index)=>{return index}" bordered :pagination="false"/>
    <div class="chartCard">
      <a-card title="部分一" style="width: 33%;height: 350px;">

      </a-card>
      <a-card title="部分二" style="width: 33%;">

      </a-card>
      <a-card title="部分三" style="width: 33%;">

      </a-card>
    </div>
  </div>

</template>
<script>
  import {getAction} from "../../api/manage";
  import UsageChart from "./components/UsageChart";

  export default {
    data() {
      return {
        SysInfo:[],
        dateTime:[],
        memoryUsage:[],
        columns:[
          {
            title: '主机名',
            dataIndex: 'hostName',
            align:"center"
          },
          {
            title:'操作系统',
            dataIndex:'osName',
            align:"center"
          },
          {
            title: '版本',
            dataIndex: 'version',
            align:'center'
          },
          {
            title:"CPU核数",
            dataIndex:'cpuCoreNum',
            align:'center'
          },
          {
            title: '内存大小',
            dataIndex: 'memorySize',
            align:"center"
          },
          {
            title: '外存大小',
            dataIndex: 'storageSize',
            align:"center"
          },
          {
            title: '创建日期',
            dataIndex: 'createTime',
            align:"center"
          }
        ],

      url:{
          systemInfo:'/sys/systemInfo'
        }
      };
    },
    mounted() {
      this.getSystemInfo();
      this.getTime();
    },
    methods:{
      getSystemInfo(){
        getAction(this.url.systemInfo,{}).then((res)=>{
          res.data.data["memorySize"] = res.data.data["memorySize"]+"G";
          res.data.data["storageSize"] = res.data.data["storageSize"]+"G";
          this.SysInfo = [res.data.data];
        })
      },
      getTime(){
        const that = this;
        window.setInterval(function () {
          var time = that.$moment().format("HH:mm:ss");
          if(that.dateTime.length>=10){
            that.dateTime.shift();
            that.dateTime.push(time);
          }
          else{
            that.dateTime.push(time);
          }
        },1000);
      }
    }
  };
</script>
<style lang="less" scoped>
  .main{
    padding:10px 10px;
    .systemBaseInfo{

    }
    .chartCard{
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      padding-top:10px;
      height:100%;
    }
  }
</style>
