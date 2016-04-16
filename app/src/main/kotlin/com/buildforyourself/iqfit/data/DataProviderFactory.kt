package com.buildforyourself.iqfit.data

class DataProviderFactory private constructor(){
    private object Holder { val INSTANCE = DataProviderFactory() }

    companion object {
        val instance: DataProviderFactory by lazy { Holder.INSTANCE }
    }

    val dataProvider : IDataProvider = FakeDataProvider()
}
