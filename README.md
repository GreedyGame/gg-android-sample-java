# GreedyGame Sample App

SDKX Version 0.0.71

Last Updated 15th July 2020

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
    implementation 'com.greedygame.sdkx:core:0.0.70'
}
```
#### Currently under EAP

# License
The code for the sample app is provided under MIT License.
