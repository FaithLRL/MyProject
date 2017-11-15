/**
 * Created by Administrator on 2017/10/17.
 */
let path = require('path'),
    webpack = require('webpack'),
    HtmlWebpackPlugin = require('html-webpack-plugin'),
    FriendlyErrorsWebpackPlugin = require('friendly-errors-webpack-plugin');
const config = {
    entry: {
        app: './www/main.js'
    },
    output: {
        publicPath: '/',
        path: path.resolve(__dirname, 'dist'),
        filename:'js/[name].js'
    },
    devServer:{
        port:9090,
        quiet:true,
        inline:true,
       /* hot:true,*/
        open:true,
        clientLogLevel: "none",
        overlay:{
            errors:true
        }
    },
    devtool: '#cheap-module-eval-source-map',
    resolve:{
        extensions: ['.js', '.vue'],
        alias: {
            'vue': 'vue/dist/vue.js',
            '@': path.resolve(__dirname, 'www')
        }
    },
    module:{
        rules:[
            {test:/\.vue$/,loader:'vue-loader'},
            {test:/\.js$/,loader:'babel-loader',exclude:/node_modules/},
            {test:/\.(jpg|png|gif)$/,loader:'url-loader',options:{limit:10000,name:'img/[name].[ext]'}}
        ]
    },
    plugins:[
        new HtmlWebpackPlugin({
            template:'./www/index.html',
            filename:'index.html'
        }),
        /*new webpack.HotModuleReplacementPlugin(),*/
        new webpack.DefinePlugin({   //设置系统环境变量
            'process.env': {
                NODE_ENV: '"development"'
            }
        }),
        new FriendlyErrorsWebpackPlugin()
    ]
};


module.exports = config;
