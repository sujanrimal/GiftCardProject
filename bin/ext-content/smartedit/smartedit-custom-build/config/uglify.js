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
module.exports = function() {

    return {
        targets: [
            'uglifyThirdparties'
        ],
        config: function(data, conf) {
            var paths = require('../paths');

            return {

                uglifyThirdparties: {
                    files: {
                        'node_modules/angular-ui-bootstrap/dist/ui-bootstrap-tpls.min.js': ['node_modules/angular-ui-bootstrap/dist/ui-bootstrap-tpls.js'],
                        'node_modules/crypto-js/crypto-js.min.js': ['node_modules/crypto-js/crypto-js.js'],
                        'node_modules/reflect-metadata/Reflect.min.js': ['node_modules/reflect-metadata/Reflect.js'],
                        'node_modules/angular-mocks/angular-mocks.min.js': ['node_modules/angular-mocks/angular-mocks.js'],
                        'node_modules/intersection-observer/intersection-observer.min.js': ['node_modules/intersection-observer/intersection-observer.js'],
                    },
                    options: {
                        mangle: true
                    }

                }
            };
        }
    };
};
