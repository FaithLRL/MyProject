/**
 * Created by Administrator on 2017/10/17.
 */
let path = require('path'),
    webpack = require('webpack'),
    pkg = require('./package.json'),
    HtmlWebpackPlugin = require('html-webpack-plugin'),
    ExtractTextWebpackPlugin=require('extract-text-webpack-plugin'),
    OptimizeCssAssetsWebpackPlugin= require('optimize-css-assets-webpack-plugin'),
    InlineManifestWebpackPlugin=require('inline-manifest-webpack-plugin');
    CleanWebpackPlugin=require('clean-webpack-plugin');
const config = {
    entry: {
        app: './www/main.js',
        vendor:Object.keys(pkg.dependencies)
    },
    output: {
        publicPath: '/vueStudy/dist/',
        path: path.resolve(__dirname, 'dist'),
        filename:'js/[name]-[chunkhash:5].js',
        chunkFilename:'js/[name]-[chunkhash:5].js'
    },
    devtool: 'source-map',
    resolve:{
        extensions: ['.js', '.vue'],
        alias: {
            'vue': 'vue/dist/vue.js',
            '@': path.resolve(__dirname, 'www')
        }
    },
    module:{
      rules:[
          {test:/\.vue$/,loader:'vue-loader',options:{loaders:{
              css: ExtractTextWebpackPlugin.extract({
                  use: [{
                      loader: 'css-loader',
                      options:{
                          minimize: true //css压缩
                      }
                  }],
                  fallback: 'vue-style-loader'
              }),
              scss: ExtractTextWebpackPlugin.extract({
                  use: [{
                      loader: 'css-loader',
                      options:{
                          minimize: true //css压缩
                      }
                  },{loader:'sass-loader'}],
                  fallback: 'vue-style-loader'
              })
          },// 补齐css浏览器前缀，引入css无效，引入scss或者less或者style里面写样式有效
              postcss: [require('autoprefixer')(
                  {browsers: ['last 10 Chrome versions', 'last 5 Firefox versions', 'Safari >= 6', 'ie > 8']})]}},
          {test:/\.js$/,loader:'babel-loader',exclude:/node_modules/},
          {test:/\.(jpg|png|gif)$/,use:[
              {loader:'url-loader',options:{limit:10000,name:'img/[name]-[hash:5].[ext]'}},
              {loader: 'image-webpack-loader'}
          ]}
      ]
    },
    plugins:[
        new HtmlWebpackPlugin({
            template:path.resolve(__dirname,'index.html'),
            filename:'index.html',
            minify: {
                removeComments: true,
               /* collapseWhitespace: true,*/
                removeAttributeQuotes: true
            },
        }),
        new webpack.DefinePlugin({   //设置系统环境变量
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            },
            sourceMap: true
        }),
        new CleanWebpackPlugin(['dist']),
        new webpack.HashedModuleIdsPlugin(),
        new webpack.optimize.CommonsChunkPlugin({
            name:'vendor'
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: 'manifest',
            chunks: ['vendor']
        }),
        new InlineManifestWebpackPlugin(),
        new ExtractTextWebpackPlugin({
            filename:'css/[name]-[contenthash:5].css',
            allChunks:true
        }),
        new OptimizeCssAssetsWebpackPlugin(),

    ]
};
module.exports = config;