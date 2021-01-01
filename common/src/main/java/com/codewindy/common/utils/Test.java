package com.codewindy.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

@Slf4j
public class Test {
    public static void main(String[] args) throws IOException {
        String url ="https://space.dingtalk.com/attachment/mdown?biztype=file&bizid=3880545389&objectid=%2F02--%E5%9F%BA%E9%87%91%E6%8A%95%E8%B5%84%2F%E6%8A%95%E8%B5%84%E7%90%86%E8%B4%A2-34%E8%AE%B23.7%E6%8C%87%E6%95%B0%E8%AF%84%E4%BC%B0%E5%80%BC%E4%B9%8B%E5%8D%9A%E6%A0%BC%E5%85%AC%E5%BC%8F.mp4";
        HttpUtil.downloadFile(url ,"");
        //downloadDingTalkVideo();
    }

    private static void downloadDingTalkVideo() throws UnsupportedEncodingException {
        // 1. 使用Fiddle Web Debugger 配置dingtalk通过内置浏览器代理登录
        // 2. 通过fiddle 解密https 获取下载和登录的接口路径和参数
        // 3. 登录web 端 https://space.dingtalk.com/index_v2.html 获取文件的bizid和objectid
        // 4. 通过web获取到 https://space.dingtalk.com/v1/space/folder/listBySize?spaceId=3880545389&folderId=26803045625&size=50&top=1&sort=21&_=1609478818138
        //     返回的json数据通过取出path的字段并通过URLEncoder.encode转义和拼接下载接口地址链接https://space.dingtalk.com/attachment/mdown?biztype=file&bizid=3880545389&objectid=
        // 5.  取出文件夹的下载txt逐个在浏览器下载 (本可以通过java自动下载 cookie识别还有问题没法实现)

        String str ="[{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:54:46\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801899745\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-31读懂基金名称A.B.C.mp4\",\"path\":\"/02--基金投资/投资理财-31读懂基金名称A.B.C.mp4\",\"size\":99429164,\"sortField\":\"投资理财-31读懂基金名称A.B.C.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:54:46\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:54:35\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801899570\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-30读懂国内指数基金的三种类型.mp4\",\"path\":\"/02--基金投资/投资理财-30读懂国内指数基金的三种类型.mp4\",\"size\":98315215,\"sortField\":\"投资理财-30读懂国内指数基金的三种类型.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:54:35\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:54:31\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801899518\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-29讲场外和场内基金-附操作指南.mp4\",\"path\":\"/02--基金投资/投资理财-29讲场外和场内基金-附操作指南.mp4\",\"size\":132071394,\"sortField\":\"投资理财-29讲场外和场内基金-附操作指南.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:54:31\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:54:27\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801869761\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-26讲基金投资4.mp4\",\"path\":\"/02--基金投资/投资理财-26讲基金投资4.mp4\",\"size\":269341558,\"sortField\":\"投资理财-26讲基金投资4.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:54:27\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:53:58\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801840141\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-28讲其它市场主流指数.mp4\",\"path\":\"/02--基金投资/投资理财-28讲其它市场主流指数.mp4\",\"size\":138537851,\"sortField\":\"投资理财-28讲其它市场主流指数.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:53:58\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:53:28\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801776956\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-27讲策略指数.mp4\",\"path\":\"/02--基金投资/投资理财-27讲策略指数.mp4\",\"size\":117867536,\"sortField\":\"投资理财-27讲策略指数.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:53:28\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:52:56\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801697561\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-25讲基金投资3.mp4\",\"path\":\"/02--基金投资/投资理财-25讲基金投资3.mp4\",\"size\":321577671,\"sortField\":\"投资理财-25讲基金投资3.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:52:56\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:52:34\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801697327\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-23讲基金投资1.mp4\",\"path\":\"/02--基金投资/投资理财-23讲基金投资1.mp4\",\"size\":346204234,\"sortField\":\"投资理财-23讲基金投资1.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:52:34\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:52:30\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801658865\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-24讲基金投资2.mp4\",\"path\":\"/02--基金投资/投资理财-24讲基金投资2.mp4\",\"size\":303231310,\"sortField\":\"投资理财-24讲基金投资2.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:52:30\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:50:37\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801466216\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-57讲基金定投高级篇.mp4\",\"path\":\"/02--基金投资/投资理财-57讲基金定投高级篇.mp4\",\"size\":147940396,\"sortField\":\"投资理财-57讲基金定投高级篇.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:50:37\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:50:12\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801442327\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-58讲注册制对我们每个人深远影响，必听.mp4\",\"path\":\"/02--基金投资/投资理财-58讲注册制对我们每个人深远影响，必听.mp4\",\"size\":66853534,\"sortField\":\"投资理财-58讲注册制对我们每个人深远影响，必听.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:50:12\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:49:54\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801408729\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-55讲为什么大v的作业搬过来还是亏钱呢.mp4\",\"path\":\"/02--基金投资/投资理财-55讲为什么大v的作业搬过来还是亏钱呢.mp4\",\"size\":153485285,\"sortField\":\"投资理财-55讲为什么大v的作业搬过来还是亏钱呢.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:49:54\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:49:43\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801381228\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-56讲原油宝的故事，你知道不？.mp4\",\"path\":\"/02--基金投资/投资理财-56讲原油宝的故事，你知道不？.mp4\",\"size\":96463732,\"sortField\":\"投资理财-56讲原油宝的故事，你知道不？.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:49:43\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:49:31\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801352483\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-54讲原油和未来十年房价.mp4\",\"path\":\"/02--基金投资/投资理财-54讲原油和未来十年房价.mp4\",\"size\":134425734,\"sortField\":\"投资理财-54讲原油和未来十年房价.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:49:31\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:49:02\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801289542\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-53讲周定投PK月定投.mp4\",\"path\":\"/02--基金投资/投资理财-53讲周定投PK月定投.mp4\",\"size\":86389822,\"sortField\":\"投资理财-53讲周定投PK月定投.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:49:02\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:48:49\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801289345\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-52讲为何自己的定投坚持不到牛市.mp4\",\"path\":\"/02--基金投资/投资理财-52讲为何自己的定投坚持不到牛市.mp4\",\"size\":81688115,\"sortField\":\"投资理财-52讲为何自己的定投坚持不到牛市.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:48:49\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:48:27\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801225995\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-51讲最近底部区域个别关注标的物.mp4\",\"path\":\"/02--基金投资/投资理财-51讲最近底部区域个别关注标的物.mp4\",\"size\":86723574,\"sortField\":\"投资理财-51讲最近底部区域个别关注标的物.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:48:27\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:48:23\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801225920\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-49什么时候到市场底部？.mp4\",\"path\":\"/02--基金投资/投资理财-49什么时候到市场底部？.mp4\",\"size\":147941562,\"sortField\":\"投资理财-49什么时候到市场底部？.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:48:23\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:48:13\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801225724\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-50讲基金的内幕.mp4\",\"path\":\"/02--基金投资/投资理财-50讲基金的内幕.mp4\",\"size\":86060557,\"sortField\":\"投资理财-50讲基金的内幕.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:48:13\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:47:47\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801171484\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-48讲T+0美日撤资各种热点问题解读.mp4\",\"path\":\"/02--基金投资/投资理财-48讲T+0美日撤资各种热点问题解读.mp4\",\"size\":142898136,\"sortField\":\"投资理财-48讲T+0美日撤资各种热点问题解读.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:47:47\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:47:32\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801137647\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-47讲为什么说微笑曲线最美.mp4\",\"path\":\"/02--基金投资/投资理财-47讲为什么说微笑曲线最美.mp4\",\"size\":153113838,\"sortField\":\"投资理财-47讲为什么说微笑曲线最美.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:47:32\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:47:14\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801113304\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-46讲教你每月定投多少合适.mp4\",\"path\":\"/02--基金投资/投资理财-46讲教你每月定投多少合适.mp4\",\"size\":121646422,\"sortField\":\"投资理财-46讲教你每月定投多少合适.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:47:14\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:46:47\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801050736\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-44讲分级基金+美元加息和降息对我们的影响？.mp4\",\"path\":\"/02--基金投资/投资理财-44讲分级基金+美元加息和降息对我们的影响？.mp4\",\"size\":219268244,\"sortField\":\"投资理财-44讲分级基金+美元加息和降息对我们的影响？.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:46:47\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:46:23\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26801020264\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-45讲基金定投亏损了怎么办？.mp4\",\"path\":\"/02--基金投资/投资理财-45讲基金定投亏损了怎么办？.mp4\",\"size\":142318907,\"sortField\":\"投资理财-45讲基金定投亏损了怎么办？.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:46:23\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:46:19\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800979396\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-43讲定投什么指数最好？汇率+降息+股市三者关系.mp4\",\"path\":\"/02--基金投资/投资理财-43讲定投什么指数最好？汇率+降息+股市三者关系.mp4\",\"size\":231258813,\"sortField\":\"投资理财-43讲定投什么指数最好？汇率+降息+股市三者关系.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:46:19\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:45:22\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800914625\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-42讲ETF如何套利.mp4\",\"path\":\"/02--基金投资/投资理财-42讲ETF如何套利.mp4\",\"size\":149062806,\"sortField\":\"投资理财-42讲ETF如何套利.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:45:22\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:45:10\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800887683\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-41讲ETF9的九种交易策略下.mp4\",\"path\":\"/02--基金投资/投资理财-41讲ETF9的九种交易策略下.mp4\",\"size\":165445346,\"sortField\":\"投资理财-41讲ETF9的九种交易策略下.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:45:10\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:44:37\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800803879\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-40讲ETF的9种投资策略.mp4\",\"path\":\"/02--基金投资/投资理财-40讲ETF的9种投资策略.mp4\",\"size\":106765389,\"sortField\":\"投资理财-40讲ETF的9种投资策略.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:44:37\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:44:10\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800767481\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-38讲ETF基金1.mp4\",\"path\":\"/02--基金投资/投资理财-38讲ETF基金1.mp4\",\"size\":169015503,\"sortField\":\"投资理财-38讲ETF基金1.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:44:10\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:43:57\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800716268\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-39沪深交易所ETF交易模式.mp4\",\"path\":\"/02--基金投资/投资理财-39沪深交易所ETF交易模式.mp4\",\"size\":86732527,\"sortField\":\"投资理财-39沪深交易所ETF交易模式.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:43:57\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:43:56\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800716251\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-37讲4.6选择指数基金的几个小技巧.mp4\",\"path\":\"/02--基金投资/投资理财-37讲4.6选择指数基金的几个小技巧.mp4\",\"size\":147130661,\"sortField\":\"投资理财-37讲4.6选择指数基金的几个小技巧.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:43:56\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:43:17\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800676519\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-36讲4.3一节了解指数基金费用.mp4\",\"path\":\"/02--基金投资/投资理财-36讲4.3一节了解指数基金费用.mp4\",\"size\":128188773,\"sortField\":\"投资理财-36讲4.3一节了解指数基金费用.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:43:17\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:42:55\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800638401\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-35讲4.2了解指数基金跟踪误差那些事.mp4\",\"path\":\"/02--基金投资/投资理财-35讲4.2了解指数基金跟踪误差那些事.mp4\",\"size\":70929255,\"sortField\":\"投资理财-35讲4.2了解指数基金跟踪误差那些事.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:42:55\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:42:50\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800638352\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-32讲超额收益怎么来的？.mp4\",\"path\":\"/02--基金投资/投资理财-32讲超额收益怎么来的？.mp4\",\"size\":178606141,\"sortField\":\"投资理财-32讲超额收益怎么来的？.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:42:50\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:42:26\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800586795\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-33讲直播答疑-2700我已经快90%仓位了.mp4\",\"path\":\"/02--基金投资/投资理财-33讲直播答疑-2700我已经快90%仓位了.mp4\",\"size\":110870948,\"sortField\":\"投资理财-33讲直播答疑-2700我已经快90%仓位了.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:42:26\"},{\"attribute\":{\"forbidSpread\":false,\"forbidSpreadExtend\":false,\"watermark\":false,\"watermarkExtend\":false},\"author\":\"7918225\",\"authorName\":\"吾爱肖老师\",\"commentCount\":0,\"contentType\":\"video\",\"createAt\":\"2020-12-04 14:42:25\",\"encrypt\":0,\"ext\":\"mp4\",\"id\":\"26800586764\",\"isSoft\":false,\"likeCount\":0,\"name\":\"投资理财-34讲3.7指数评估值之博格公式.mp4\",\"path\":\"/02--基金投资/投资理财-34讲3.7指数评估值之博格公式.mp4\",\"size\":88490634,\"sortField\":\"投资理财-34讲3.7指数评估值之博格公式.mp4\",\"spaceId\":3880545389,\"spaceType\":\"group\",\"type\":\"file\",\"updateAt\":\"2020-12-04 14:42:25\"}]";
        JSONArray jsonArray = JSON.parseArray(str);
        ArrayList<Object> list = Lists.newArrayListWithCapacity(30);
        String url="https://space.dingtalk.com/attachment/mdown?biztype=file&bizid=3880545389&objectid=";
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            // 使用 https://www.sojson.com/encodeurl.html 的encodeURIComponent 前端方法重新转义参数中的特殊符号
            list.add(url+ URLEncoder.encode(jsonObject.get("path").toString(), "utf-8"));
           // list.add(url+ jsonObject.get("path")); 未转义的参数

        }
        FileUtil.writeUtf8Lines(list, "D:\\20210101\\2_encoded.txt");
    }
}
