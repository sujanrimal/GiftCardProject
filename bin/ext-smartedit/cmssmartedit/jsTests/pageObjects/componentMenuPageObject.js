/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
/* jshint unused:false, undef:false */
module.exports = (function() {

    var pageObject = {};

    pageObject.constants = {};

    pageObject.actions = {
        navigateToPage: function(dirName) {
            return browser.bootstrap(dirName).then(function() {
                return browser.waitForWholeAppToBeReady();
            });
        }
    };

    return pageObject;
}());
