package com.example.notification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notification.ui.theme.NotificationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Your UI components go here
                    val postNotificationPermission =
                        rememberPermissionState(permission = "android.permission.POST_NOTIFICATIONS")

                    val waterNotificationService = WaterNotificationService(this)

                    LaunchedEffect(key1 = true) {
                        if (!postNotificationPermission.status.isGranted) {
                            postNotificationPermission.launchPermissionRequest()
                        }
                    }

                    Column {
                        Button(
                            onClick = {
                                waterNotificationService.showBasicNotification()
                            }
                        ) {
                            Text(text = "Show basic notification")
                        }

                        Button(
                            onClick = {
                                waterNotificationService.showExpandableNotification()
                            }
                        ) {
                            Text(text = "Show expandable with image notification")
                        }

                        Button(
                            onClick = {
                                waterNotificationService.showExpandableLongText()
                            }
                        ) {
                            Text(text = "Show expandable with text notification")
                        }

                        Button(
                            onClick = {
                                waterNotificationService.showInboxStyleNotification()
                            }
                        ) {
                            Text(text = "Show inbox-style notification")
                        }

                        Button(
                            onClick = {
                                waterNotificationService.showNotificationGroup()
                            }
                        ) {
                            Text(text = "Show inbox-style notification group")
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotificationTheme {
        Greeting("Android")
    }
}