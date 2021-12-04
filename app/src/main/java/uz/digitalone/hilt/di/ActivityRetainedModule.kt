package uz.digitalone.hilt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.digitalone.hilt.repo.AuthRepo
import uz.digitalone.hilt.repo_imp.AuthRepoImp

@Module
@InstallIn(ActivityRetainedComponent::class)
 abstract class ActivityRetainedModule {
     @Binds
abstract fun dataSource(imp:AuthRepoImp):AuthRepo
}