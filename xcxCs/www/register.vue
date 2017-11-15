<template>
    <div class="box">
        账号：<input  type="text" v-model="username" /><br/>
        密码：<input class="password" type="text" v-model="password" />
        <button class="btn" @click="register">注册</button>

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
                    url: 'http://localhost:8080/save?number='+this.username+'&&password='+this.password,
                    method: 'Get',
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
</style>