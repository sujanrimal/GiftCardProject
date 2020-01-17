package com.nhogiftcard.core.enums;

import de.hybris.platform.core.HybrisEnumValue;

/**
 * Generated enum GiftCardTypeEnum declared at extension nhogiftcardcore.
 */
@SuppressWarnings("PMD")
public enum GiftCardTypeEnum implements HybrisEnumValue
{
	/**
	 * Generated enum value for GiftCardTypeEnum.giftcard25 declared at extension nhogiftcardcore.
	 */
	GIFTCARD25("giftcard25"),
	/**
	 * Generated enum value for GiftCardTypeEnum.giftcard50 declared at extension nhogiftcardcore.
	 */
	GIFTCARD50("giftcard50"),
	/**
	 * Generated enum value for GiftCardTypeEnum.giftcard75 declared at extension nhogiftcardcore.
	 */
	GIFTCARD75("giftcard75"),
	/**
	 * Generated enum value for GiftCardTypeEnum.giftcard100 declared at extension nhogiftcardcore.
	 */
	GIFTCARD100("giftcard100");
	 
	/**<i>Generated model type code constant.</i>*/
	public final static String _TYPECODE = "GiftCardTypeEnum";
	
	/**<i>Generated simple class name constant.</i>*/
	public final static String SIMPLE_CLASSNAME = "GiftCardTypeEnum";
	
	/** The code of this enum.*/
	private final String code;
	
	/**
	 * Creates a new enum value for this enum type.
	 *  
	 * @param code the enum value code
	 */
	private GiftCardTypeEnum(final String code)
	{
		this.code = code.intern();
	}
	
	
	/**
	 * Gets the code of this enum value.
	 *  
	 * @return code of value
	 */
	@Override
	public String getCode()
	{
		return this.code;
	}
	
	/**
	 * Gets the type this enum value belongs to.
	 *  
	 * @return code of type
	 */
	@Override
	public String getType()
	{
		return SIMPLE_CLASSNAME;
	}
	
}
