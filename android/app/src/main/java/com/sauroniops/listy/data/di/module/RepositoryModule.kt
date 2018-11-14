package com.sauroniops.listy.data.di.module

import com.sauroniops.listy.data.repository.ChecklistRepository
import com.sauroniops.listy.data.repository.ChecklistRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val repositoryModule = Kodein.Module(name = "RepositoryModule") {

    bind<ChecklistRepository>() with singleton {
        ChecklistRepositoryImpl(instance())
    }

}
