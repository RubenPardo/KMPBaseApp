package di

import di.data.dataModule
import di.domain.domainModule
import di.presentation.presentationModule
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(presentationModule, domainModule, dataModule)
        }
    }
}