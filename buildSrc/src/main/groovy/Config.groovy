/**
 * create on 19/09/11
 * gradle 配置管理
 * @author ListenerGao
 */
class Config {

    protected static applicationId = 'com.listenergao.recyclerviewdemo'
    protected static compileSdkVersion = 29
    protected static buildToolsVersion = '29.0.2'
    protected static minSdkVersion = 19
    protected static targetSdkVersion = 29

    protected static versionCode = 1
    protected static versionName = '1.0'

    protected static kotlin_version = '1.3.50'
    protected static kotlin_coroutines = '1.3.1'


    protected static depConfig = [

            junit            : 'junit:junit:4.12',
            runner           : 'androidx.test:runner:1.2.0',
            espresso_core    : 'androidx.test.espresso:espresso-core:3.2.0',


            appcompat        : 'androidx.appcompat:appcompat:1.1.0',
            constraintlayout : 'androidx.constraintlayout:constraintlayout:1.1.3',
            recyclerview     : 'androidx.recyclerview:recyclerview:1.0.0',
            material         : 'com.google.android.material:material:1.0.0',
            legacy_support_v4: 'androidx.legacy:legacy-support-v4:1.0.0',


            statusbarutil    : 'com.jaeger.statusbarutil:library:1.5.1'


    ]
}