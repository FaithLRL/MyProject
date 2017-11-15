<template>
    <div class="box">
        <button class="btn" @click="register">查询所有用户</button>
           <table>
              <tr>
                 <th>id</th>
                 <th>学号</th>
                 <th>用户名</th>
                 <th>密码</th>
                 <th>性别</th>
              </tr>
              <tr v-cloak v-for="(item, index) of slist">
                  <td>{{index+1}}</td>
                  <td>{{item.username}}</td>
                   <td>{{item.email}}</td>
                   <td>{{item.sex}}</td>
                   <td>{{item.province}}</td>
              </tr>
           </table>
    </div>
</template>
<script>
    import axios from 'axios';
    export default {
        data(){
            return {
                username:'',
                password:''
            }
        },
        methods:{
            register(){
                axios({
                    url: 'http://localhost:8089/login?number='+this.username+'&password='+this.password,
                }).then(function (response) {
                    console.log(response)
                    alert(response.data.msg)
                })

            }
        }
    }
</script>
<style>
    .box{
        width:340px;
        margin: 200px auto;
    }

    .password{
        margin-top: 10px;
    }
    .btn{
        width: 100px;
        margin-top: 12px;
        margin-left: 78px;
        display: block;
    }
    [v-cloak] {
        display: none
    }
    table {
        border: 1px solid #ccc;
        padding: 0;
        border-collapse: collapse;
        table-layout: fixed;
        margin-top: 10px;
        width: 100%;
    }
    table td, table th {
        height: 30px;
        border: 1px solid #ccc;
        background: #fff;
        font-size: 15px;
        padding: 3px 3px 3px 8px;
    }
    table th:first-child {
        width: 30px;
    }
    .container,
    .st {
        width: 1000px;
        margin: 10px auto 0;
        font-size: 13px;
        font-family: 'Microsoft YaHei'
    }
    .container .search {
        font-size: 15px;
        padding: 4px;
    }
    .container .add {
        padding: 5px 15px;
    }
    .overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 6;
        background: rgba(0, 0, 0, 0.7);
    }
    .overlay td:first-child {
        width: 66px;
    }
    .overlay .con {
        position: absolute;
        width: 420px;
        min-height: 300px;
        background: #fff;
        left: 50%;
        top: 50%;
        -webkit-transform: translate3d(-50%, -50%, 0);
        transform: translate3d(-50%, -50%, 0);
        /*margin-top: -150px;*/
        padding: 20px;
    }
</style>
</style>