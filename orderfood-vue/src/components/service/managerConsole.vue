<template>
    <div style="float:left;">
        <div style="float:left;width:700px;">
            <el-card class="box-card">
                <div class="console">
                    <el-collapse v-model="activeNames">
                        <el-collapse-item title="项目介绍" name="1">
                            爱吃火锅点餐小程序由小程序端和管理端构成，小程序端是用微信小程序原生语言开发，管理端是前后端分离的Web系统，前端是用Vue+Elementui开发，后端是用SpirngBoot+MyBatis开发，数据库用的是mysql。总体来说涉及的技术比较广，都是市面上主流的技术，非常适合作为毕业设计。
                        </el-collapse-item>
                        <el-collapse-item title="使用技术" name="2">
                            <div>
                                <el-tag>微信小程序</el-tag>
                                <el-tag>SpringBoot</el-tag>
                                <el-tag>Vue2.0</el-tag>
                                <el-tag>Elementui</el-tag>
                                <el-tag>jwt</el-tag>
                                <el-tag>MyBatis</el-tag>
                                <el-tag>Mysql</el-tag>
                            </div>
                        </el-collapse-item>
                        <el-collapse-item title="功能介绍" name="3">
                            <div>权限管理：包含菜单管理、用户管理、角色管理、部门管理、用户权限分配。</div>
                            <div>小程序管理：包含菜品种类、菜品管理、订单管理、会员等级。</div>
                        </el-collapse-item>
                    </el-collapse>
                </div>
            </el-card>
        </div>
        <div style="float:left;width:550px;margin-left: 20px;">
            <el-card class="box-card">
                <div class="console">
                    <el-collapse v-model="person">
                        <el-collapse-item title="开发者信息" name="5">
                            由于项目咨询的人过多,实在分身乏术，因此决定将提供有偿咨询服务。如果您在安装或者开发学习中遇到困难，可以联系本人提供有偿服务。
                            <div>
                                <el-image style="width:100px;height:100px;" src="../../../static/images/mine.jpg">
                                </el-image>
                            </div>
                            定价如下：<br>

                            1.如果项目在运行过程中出现问题无法处理，35元技术服务费，帮你查找问题。如果你是纯白丁什么都不懂，开发环境都没有，加微信沟通，价格另议。<br>

                            2.如果你对系统如何使用不熟悉，35元项目讲解费，帮你讲解系统使用流程及一些注意事项。<br>

                            3.如果你需要对项目进行一些修改，或者开发一些新功能，加微信沟通。<br>

                            4.其他服务：技术教学、定制开发、面试咨询等，加微信沟通。<br>
                        </el-collapse-item>
                        <el-collapse-item title="个人公众号" name="7">
                            如果本项目不适合你，没关系。关注本人公众号，里面还有更多开源项目等着你。
                            <div>
                                <el-image style="width:100px;height:100px;" src="../../../static/images/gongzhonghao.jpg">
                                </el-image>
                            </div>
                        </el-collapse-item>
                    </el-collapse>
                </div>
            </el-card>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            activeNames: ['1', '2', '3'],
            person: ['5', '6', '7']
        };
    },
    methods: {
    }
}
</script>
<style>
.console .el-collapse-item__header {
    font-size: 16px;
    font-weight: bold;
}

.console .el-collapse-item__content {
    font-size: 15px;
    line-height: 35px;
}
</style>