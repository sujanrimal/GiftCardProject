/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 17, 2020 11:49:26 AM                    ---
 * ----------------------------------------------------------------
 */
package com.nhogiftcard.core.jalo;

import com.nhogiftcard.core.constants.NhogiftcardCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem GiftCard}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedGiftCard extends GenericItem
{
	/** Qualifier of the <code>GiftCard.amount</code> attribute **/
	public static final String AMOUNT = "amount";
	/** Qualifier of the <code>GiftCard.giftcardnum</code> attribute **/
	public static final String GIFTCARDNUM = "giftcardnum";
	/** Qualifier of the <code>GiftCard.giftcardpin</code> attribute **/
	public static final String GIFTCARDPIN = "giftcardpin";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(AMOUNT, AttributeMode.INITIAL);
		tmp.put(GIFTCARDNUM, AttributeMode.INITIAL);
		tmp.put(GIFTCARDPIN, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.amount</code> attribute.
	 * @return the amount - The amount of the giftcard.
	 */
	public Double getAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.amount</code> attribute.
	 * @return the amount - The amount of the giftcard.
	 */
	public Double getAmount()
	{
		return getAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.amount</code> attribute. 
	 * @return the amount - The amount of the giftcard.
	 */
	public double getAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.amount</code> attribute. 
	 * @return the amount - The amount of the giftcard.
	 */
	public double getAmountAsPrimitive()
	{
		return getAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.amount</code> attribute. 
	 * @param value the amount - The amount of the giftcard.
	 */
	public void setAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, AMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.amount</code> attribute. 
	 * @param value the amount - The amount of the giftcard.
	 */
	public void setAmount(final Double value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.amount</code> attribute. 
	 * @param value the amount - The amount of the giftcard.
	 */
	public void setAmount(final SessionContext ctx, final double value)
	{
		setAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.amount</code> attribute. 
	 * @param value the amount - The amount of the giftcard.
	 */
	public void setAmount(final double value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.giftcardnum</code> attribute.
	 * @return the giftcardnum - The 10 digit number of the giftcard.
	 */
	public String getGiftcardnum(final SessionContext ctx)
	{
		return (String)getProperty( ctx, GIFTCARDNUM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.giftcardnum</code> attribute.
	 * @return the giftcardnum - The 10 digit number of the giftcard.
	 */
	public String getGiftcardnum()
	{
		return getGiftcardnum( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.giftcardnum</code> attribute. 
	 * @param value the giftcardnum - The 10 digit number of the giftcard.
	 */
	public void setGiftcardnum(final SessionContext ctx, final String value)
	{
		setProperty(ctx, GIFTCARDNUM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.giftcardnum</code> attribute. 
	 * @param value the giftcardnum - The 10 digit number of the giftcard.
	 */
	public void setGiftcardnum(final String value)
	{
		setGiftcardnum( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.giftcardpin</code> attribute.
	 * @return the giftcardpin - The 6 digit pin of the giftcard.
	 */
	public String getGiftcardpin(final SessionContext ctx)
	{
		return (String)getProperty( ctx, GIFTCARDPIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>GiftCard.giftcardpin</code> attribute.
	 * @return the giftcardpin - The 6 digit pin of the giftcard.
	 */
	public String getGiftcardpin()
	{
		return getGiftcardpin( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.giftcardpin</code> attribute. 
	 * @param value the giftcardpin - The 6 digit pin of the giftcard.
	 */
	public void setGiftcardpin(final SessionContext ctx, final String value)
	{
		setProperty(ctx, GIFTCARDPIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>GiftCard.giftcardpin</code> attribute. 
	 * @param value the giftcardpin - The 6 digit pin of the giftcard.
	 */
	public void setGiftcardpin(final String value)
	{
		setGiftcardpin( getSession().getSessionContext(), value );
	}
	
}
