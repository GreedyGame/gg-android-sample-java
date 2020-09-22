# GreedyGame Sample App

SDKX Version [ ![latest](https://api.bintray.com/packages/greedygame/SDKX/com.greedygame.core/images/download.svg) ](https://bintray.com/greedygame/SDKX/com.greedygame.core/_latestVersion)

Last Updated 17th Sep 2020

# Integration
Apps can easily integrate GreedyGame SDKX with Gradle
#### Add the dependency to your app `build.gradle`
``` gradle
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    ...............
    ...............
    //greedygame sdk
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.multidex:multidex:2.0.1'
    implementation "androidx.palette:palette:1.0.0"
    implementation("com.squareup.moshi:moshi-kotlin:1.9.2")
    annotationProcessor("com.squareup.moshi:moshi-kotlin-codegen:1.9.2")
    implementation 'com.greedygame.sdkx:core:x.y.z'
}
```
#### Currently under EAP

# License
The code for the sample app is provided under MIT License.
