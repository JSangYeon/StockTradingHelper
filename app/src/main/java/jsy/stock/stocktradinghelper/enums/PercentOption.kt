package jsy.stock.stocktradinghelper.enums

enum class PercentOption(val defaultValue : Int) {

    BullishFireAddBuyoutPercent(5), // 상승장 불타기
    BullishWaterAddBuyoutPercent(5), // 상승장 물타기

    BearishFireAddBuyoutPercent(8), // 하락장 불타기
    BearishWaterAddBuyoutPercent(8) // 하락장 물타기

}