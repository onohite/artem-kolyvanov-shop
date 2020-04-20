
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.model.Product
import android.widget.TextView
import com.example.artem_kolyvanov_shop.R


class recyclerViewAdapter(val productList: List<Product>):
    RecyclerView.Adapter<recyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.products_list,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: recyclerViewAdapter.ViewHolder, position: Int) {
        var product: Product = productList[position]
        holder.name.text = product.getProductName()
        holder.price.text = product.getProductPrice().toString()
        holder.discount.text = product.getProductDiscount().toString()+"%"
        holder.sumValue.text = product.calcDiscountPrice().toString()

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        var name:TextView = item.findViewById(R.id.productName)
        var price:TextView = item.findViewById(R.id.productPrice)
        var discount:TextView = item.findViewById(R.id.discountNumber)
        var sumValue:TextView = item.findViewById(R.id.checkoutSumValue)

    }}