package model
{
  import mx.collections.ArrayCollection;
  import mx.events.CollectionEvent;

import vo.ProductVO;


/**
   * @version  $Revision: $
   */

  /**
   * Represents the shopping cart of a Cairngormstore customer.
   * @description
   * A collection of ShoppingCartElement objects that represent the current shopping cart of a user.
   * @see ShoppingCartElement
   * @see com.adobe.cairngorm.samples.store.vo.ProductVO
   */
  [Bindable]
  public class ShoppingCart
  {
    /**
     * Adds one or many products of same kind to the current shopping cart.
     *
     * @description
     * a product is represented as a Value Object, ProductVO. The quantity specifies the
     * the amount of products to add. If a product already exists, addElement() increases
     * the quantity of an existing element instead creating a new element.
     *
     * @param element Value Object ProductVO that represents a product.
     *
     * @param quantity (optional) Number of products to add. If not specified or invalid, quantity is 1.
     */
    public function addElement( element : ProductVO, quantity : Number = 1 ) : void
    {
      if( quantity <= 0 )
      {
        quantity = 1;
      }

      /*
      instead adding a new element to the shopping cart, just increase the quantity of an existing element.
      This functionality does not exist in the original Macromedia FlexStore.
      */
      for( var i : uint = 0; i < elements.length; i++ )
      {
        var shoppingCartElement : ShoppingCartElement = elements[ i ];

        if( shoppingCartElement.element.id == element.id )
        {
          shoppingCartElement.quantity += quantity;
          shoppingCartElement.totalProductPrice = shoppingCartElement.price * shoppingCartElement.quantity;
          totalProductPrice += shoppingCartElement.price * quantity;

          elements.dispatchEvent( new CollectionEvent( CollectionEvent.COLLECTION_CHANGE ) );

          return;
        }
      }

      addNewElementToCart( element, quantity );
    }

    /**
     * Deletes one or many elements of same kind of the current shopping cart.
     *
     * @param element Value Object ProductVO that represents a product.
     */
    public function deleteElement( element : ProductVO ) : Boolean
    {
      var deleted : Boolean = false;

      var i : int;
      for( i = 0; i < elements.length; i++ )
      {
        var shoppingCartElement : ShoppingCartElement = elements[ i ];
        if(shoppingCartElement.element.id === element.id)
        {
          totalProductPrice -= shoppingCartElement.totalProductPrice;
          elements.removeItemAt( i );
          deleted = true;
          break;
        }
      }
      return deleted;
    }

    public function getElements() : ArrayCollection
    {
      return elements;
    }

    /**
     * Adds a new type of element to the shopping cart.
     *
     * @param element Value Object ProductVO that represents a product.
     *
     * @param quantity (optional) Number of products to add. If not specified or invalid, quantity is 1.
     */
    private function addNewElementToCart( element : ProductVO, quantity : Number ):void
    {
      var shoppingCartElement : ShoppingCartElement = new ShoppingCartElement( element );
      shoppingCartElement.quantity = quantity;
      shoppingCartElement.name = element.name;
      shoppingCartElement.price = element.price;
      shoppingCartElement.totalProductPrice = element.price * quantity;
      elements.addItem( shoppingCartElement );
      totalProductPrice += shoppingCartElement.totalProductPrice;
    }

    /**
     * A collection of ShoppingCartElement objects that represent the content of the shopping cart.
     * @see ShoppingCartElement
     */
    public var elements : ArrayCollection = new ArrayCollection();

    /**
     * The sum of each ProductVO's price property muliplied by its quantity property.
     */
    public var totalProductPrice : Number = 0;

     /**
      * The shippingCost.
      */
    public var shippingCost : Number = 0;
  }
}
