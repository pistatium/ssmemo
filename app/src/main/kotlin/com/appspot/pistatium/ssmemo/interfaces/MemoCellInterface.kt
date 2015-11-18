package com.appspot.pistatium.ssmemo.interfaces

import com.appspot.pistatium.ssmemo.models.Memo

/**
 * Created by kimihiro on 15/11/17.
 */
interface MemoCellInterface {
    fun onClickFav(memo: Memo)
    fun onClickDelete(memo: Memo)
}
