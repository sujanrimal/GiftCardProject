/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 17, 2020 11:49:26 AM                    ---
 * ----------------------------------------------------------------
 */
package com.nhogiftcard.core.model;

import de.hybris.bootstrap.annotations.Accessor;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.model.ItemModelContext;

/**
 * Generated model class for type GiftCard first defined at extension nhogiftcardcore.
 * <p>
 * Existing gift card with 3 attributes.
 */
@SuppressWarnings("all")
public class GiftCardModel extends ItemModel
{
	/**<i>Generated model type code constant.</i>*/
	public static final String _TYPECODE = "GiftCard";
	
	/** <i>Generated constant</i> - Attribute key of <code>GiftCard.amount</code> attribute defined at extension <code>nhogiftcardcore</code>. */
	public static final String AMOUNT = "amount";
	
	/** <i>Generated constant</i> - Attribute key of <code>GiftCard.giftcardnum</code> attribute defined at extension <code>nhogiftcardcore</code>. */
	public static final String GIFTCARDNUM = "giftcardnum";
	
	/** <i>Generated constant</i> - Attribute key of <code>GiftCard.giftcardpin</code> attribute defined at extension <code>nhogiftcardcore</code>. */
	public static final String GIFTCARDPIN = "giftcardpin";
	
	
	/**
	 * <i>Generated constructor</i> - Default constructor for generic creation.
	 */
	public GiftCardModel()
	{
		super();
	}
	
	/**
	 * <i>Generated constructor</i> - Default constructor for creation with existing context
	 * @param ctx the model context to be injected, must not be null
	 */
	public GiftCardModel(final ItemModelContext ctx)
	{
		super(ctx);
	}
	
	/**
	 * <i>Generated constructor</i> - for all mandatory and initial attributes.
	 * @deprecated since 4.1.1 Please use the default constructor without parameters
	 * @param _owner initial attribute declared by type <code>Item</code> at extension <code>core</code>
	 */
	@Deprecated
	public GiftCardModel(final ItemModel _owner)
	{
		super();
		setOwner(_owner);
	}
	
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.amount</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 * @return the amount - The amount of the giftcard.
	 */
	@Accessor(qualifier = "amount", type = Accessor.Type.GETTER)
	public Double getAmount()
	{
		return getPersistenceContext().getPropertyValue(AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.giftcardnum</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 * @return the giftcardnum - The 10 digit number of the giftcard.
	 */
	@Accessor(qualifier = "giftcardnum", type = Accessor.Type.GETTER)
	public String getGiftcardnum()
	{
		return getPersistenceContext().getPropertyValue(GIFTCARDNUM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.giftcardpin</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 * @return the giftcardpin - The 6 digit pin of the giftcard.
	 */
	@Accessor(qualifier = "giftcardpin", type = Accessor.Type.GETTER)
	public String getGiftcardpin()
	{
		return getPersistenceContext().getPropertyValue(GIFTCARDPIN);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>GiftCard.amount</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 *  
	 * @param value the amount - The amount of the giftcard.
	 */
	@Accessor(qualifier = "amount", type = Accessor.Type.SETTER)
	public void setAmount(final Double value)
	{
		getPersistenceContext().setPropertyValue(AMOUNT, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>GiftCard.giftcardnum</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 *  
	 * @param value the giftcardnum - The 10 digit number of the giftcard.
	 */
	@Accessor(qualifier = "giftcardnum", type = Accessor.Type.SETTER)
	public void setGiftcardnum(final String value)
	{
		getPersistenceContext().setPropertyValue(GIFTCARDNUM, value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of <code>GiftCard.giftcardpin</code> attribute defined at extension <code>nhogiftcardcore</code>. 
	 *  
	 * @param value the giftcardpin - The 6 digit pin of the giftcard.
	 */
	@Accessor(qualifier = "giftcardpin", type = Accessor.Type.SETTER)
	public void setGiftcardpin(final String value)
	{
		getPersistenceContext().setPropertyValue(GIFTCARDPIN, value);
	}
	
}
