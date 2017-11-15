/**
 * Created by Administrator on 2017/10/10.
 */
import Vue from 'vue';
import Router from 'vue-router'
import App from './App';
import Login from './login.vue';
import Register from './register.vue';
import FindAll from './findAll.vue';
Vue.use(Router);
let router=new Router({
   routes:[
       {path:'/login',component:Login},
       {path:'/register',component:Register},
       {path:'/findAll',component:FindAll}

   ]
});

new Vue({
    el:'#app',
    components:{
      App
    },
    router,
    template:'<App/>'
});