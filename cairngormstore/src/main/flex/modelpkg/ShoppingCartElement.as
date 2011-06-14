package modelpkg {
import vo.ProductVO;

/**
 * @version  $Revision: $
 */

/**
 * Represents a single element of a shopping cart.
 * @description
 * A single element can contain a product that represents many products of the same type
 * through its quantity property.
 * @see ShoppingCart
 * @see com.adobe.cairngorm.samples.store.vo.ProductVO
 */
public class ShoppingCartElement {
    public function ShoppingCartElement(element:ProductVO) {
        this.element = element;
    }

    /**
     * Each product is represented as Value Object ProductVO.
     */
    public var element:ProductVO;

    /**
     * The number of ProductVO's of the same type.
     */
    public var quantity:Number;

    /**
     * The name property of a ProductVO for easy access.
     */
    public var name:String;

    /**
     * The price property of a ProductVO for easy access.
     */
    public var price:Number;

    /**
     * The sum of the ProductVO's price property and the quantity of ShoppingCartElement.
     */
    public var totalProductPrice:Number;
}
}
