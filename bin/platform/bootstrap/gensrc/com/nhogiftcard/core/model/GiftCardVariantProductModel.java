/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 17, 2020 11:49:26 AM                    ---
 * ----------------------------------------------------------------
 */
package com.nhogiftcard.core.model;

import com.nhogiftcard.core.enums.GiftCardTypeEnum;
import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;
import de.hybris.platform.variants.model.VariantProductModel;

/**
 * Generated model class for type GiftCardVariantProduct first defined at extension nhogiftcardcore.
 * <p>
 * Gift Card variant type contains additional attributes describing variant value.
 */
@SuppressWarnings("all")
public class GiftCardVariantProductModel extends VariantProductModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "GiftCardVariantProduct";
	
	/** <i>Generated constant</i> - Attribute key of <code>GiftCardVariantProduct.giftCardAmount</code> attribute defined at extension <code>nhogiftcardcore</code>. */
	public static final String GIFTCARDAMOUNT = "giftCardAmount";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public GiftCardVariantProductModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public GiftCardVariantProductModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - Constructor with all mandatory attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _baseProduct initial attribute declared by type <code>VariantProduct</code> at extension <code>catalog</code>
	 * @param _catalogVersion initial attribute declared by type <code>Product</code> at extension <code>catalog</code>
	 * @param _code initial attribute declared by type <code>Product</code> at extension <code>core</code>
	 */
	@Deprecated
	public GiftCardVariantProductModel(final ProductModel _baseProduct, final CatalogVersionModel _catalogVersion, final String _code)
	{
		super();
		setBaseProduct(_baseProduct);
		setCatalogVersion(_catalogVersion);
		setCode(_code);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _baseProduct initial attribute declared by type <code>VariantProduct</code> at extension <code>catalog</code>
	 * @param _catalogVersion initial attribute declared by type <code>Product</code> at extension <code>catalog</code>
	 * @param _code initial attribute declared by type <code>Product</code> at extension <code>core</code>
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 */
	@Deprecated
	public GiftCardVariantProductModel(final ProductModel _baseProduct, final CatalogVersionModel _catalogVersion, final String _code, final ItemModel _owner)
	{
		super();
		setBaseProduct(_baseProduct);
		setCatalogVersion(_catalogVersion);
		setCode(_code);
		setOwner(_owner);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCardVariantProduct.giftCardAmount</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 * @return the giftCardAmount - type of concert (indoors or open air)
	 */
	@Accessor(qualifier = "giftCardAmount", type = Accessor.Type.GETTER)
	public GiftCardTypeEnum getGiftCardAmount()
	{
		return getPersistenceContext().getPropertyValue(GIFTCARDAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>GiftCardVariantProduct.giftCardAmount</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 *  
	 * @param value the giftCardAmount - type of concert (indoors or open air)
	 */
	@Accessor(qualifier = "giftCardAmount", type = Accessor.Type.SETTER)
	public void setGiftCardAmount(final GiftCardTypeEnum value)
	{
		getPersistenceContext().setPropertyValue(GIFTCARDAMOUNT, value);
	}
	
}
