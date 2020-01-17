/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 17, 2020 11:49:26 AM                    ---
 * ----------------------------------------------------------------
 */
package com.nhogiftcard.core.jalo;

import com.nhogiftcard.core.constants.NhogiftcardCoreConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.variants.jalo.VariantProduct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.variants.jalo.VariantProduct GiftCardVariantProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedGiftCardVariantProduct extends VariantProduct
{
	/** Qualifier of the <code>GiftCardVariantProduct.giftCardAmount</code> attribute **/
	public static final String GIFTCARDAMOUNT = "giftCardAmount";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(VariantProduct.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(GIFTCARDAMOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCardVariantProduct.giftCardAmount</code> attribute.
	 * @return the giftCardAmount - type of concert (indoors or open air)
	 */
	public EnumerationValue getGiftCardAmount(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, GIFTCARDAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCardVariantProduct.giftCardAmount</code> attribute.
	 * @return the giftCardAmount - type of concert (indoors or open air)
	 */
	public EnumerationValue getGiftCardAmount()
	{
		return getGiftCardAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCardVariantProduct.giftCardAmount</code> attribute. 
	 * @param value the giftCardAmount - type of concert (indoors or open air)
	 */
	public void setGiftCardAmount(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, GIFTCARDAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCardVariantProduct.giftCardAmount</code> attribute. 
	 * @param value the giftCardAmount - type of concert (indoors or open air)
	 */
	public void setGiftCardAmount(final EnumerationValue value)
	{
		setGiftCardAmount( getSession().getSessionContext(), value );
	}
	
}
