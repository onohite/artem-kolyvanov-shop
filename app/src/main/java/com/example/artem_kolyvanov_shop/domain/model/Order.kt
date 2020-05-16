package com.example.artem_kolyvanov_shop.domain.model

data class Order(
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val paymentType: PaymentType,
    val items: List<Item>
) {
    /**
     * @return [items] mapped to List<[FullOrder.Item]> with [Product]s returned by
     * passing [Item.productId] to [findProductById].
     * If [findProductById] returned null for [Item] from [items] than this item will not be presented in result list.
     */
    inline fun toFullOrder(findProductById: (String) -> Product?): FullOrder {
        val fullOrderItems = items.mapNotNull { item ->
            item.toFullOrderItem(findProductById)
        }
        return FullOrder(
            userFirstName,
            userLastName,
            userPhone,
            paymentType,
            fullOrderItems
        )
    }

    data class Item(
        val productId: String,
        val count: Int
    ) {
        /**
         * @return [FullOrder.Item] with [Product] returned by [findProductById]
         * if this [Product] is not null and null otherwise.
         */
        inline fun toFullOrderItem(findProductById: (String) -> Product?): FullOrder.Item? {
            val product = findProductById(productId)
            return if (product != null)
                FullOrder.Item(product, count)
            else
                null
        }
    }

    enum class PaymentType {
        CashOnReceiving, CardOnReceiving
    }
}

data class FullOrder(
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val paymentType: Order.PaymentType,
    val items: List<Item>
) {
    data class Item(
        val product: Product,
        val count: Int
    )
}