/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

/** @module service-db-js/db_service */
var utils = require('vertx-js/util/utils');
var Vertx = require('vertx-js/vertx');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JDbService = com.test.db.DbService;

/**
 This is the interface for NuProcess Implementation. Created by pcjoshi on 11/27/15.

 @class
*/
var DbService = function(j_val) {

  var j_dbService = j_val;
  var that = this;

  /**

   @public
   @param procName {string} 
   @param databaseConfig {Object} 
   @param resultHandler {function} 
   */
  this.storedProc = function(procName, databaseConfig, resultHandler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'object' && typeof __args[2] === 'function') {
      j_dbService["storedProc(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](procName, utils.convParamJsonObject(databaseConfig), function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnJson(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param query {string} 
   @param databaseConfig {Object} 
   @param resultHandler {function} 
   */
  this.createStm = function(query, databaseConfig, resultHandler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'object' && typeof __args[2] === 'function') {
      j_dbService["createStm(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](query, utils.convParamJsonObject(databaseConfig), function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnJson(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param query {string} 
   @param databaseConfig {Object} 
   @param resultHandler {function} 
   */
  this.update = function(query, databaseConfig, resultHandler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'object' && typeof __args[2] === 'function') {
      j_dbService["update(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](query, utils.convParamJsonObject(databaseConfig), function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnJson(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param query {string} 
   @param databaseConfig {Object} 
   @param resultHandler {function} 
   */
  this.delete = function(query, databaseConfig, resultHandler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'object' && typeof __args[2] === 'function') {
      j_dbService["delete(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](query, utils.convParamJsonObject(databaseConfig), function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnJson(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param query {string} 
   @param databaseConfig {Object} 
   @param resultHandler {function} 
   */
  this.read = function(query, databaseConfig, resultHandler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'object' && typeof __args[2] === 'function') {
      j_dbService["read(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](query, utils.convParamJsonObject(databaseConfig), function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnJson(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @public
   @param query {string} 
   @param databaseConfig {Object} 
   @param resultHandler {function} 
   */
  this.nonSharedRead = function(query, databaseConfig, resultHandler) {
    var __args = arguments;
    if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'object' && typeof __args[2] === 'function') {
      j_dbService["nonSharedRead(java.lang.String,io.vertx.core.json.JsonObject,io.vertx.core.Handler)"](query, utils.convParamJsonObject(databaseConfig), function(ar) {
      if (ar.succeeded()) {
        resultHandler(utils.convReturnJson(ar.result()), null);
      } else {
        resultHandler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_dbService;
};

/**

 @memberof module:service-db-js/db_service

 @return {DbService}
 */
DbService.create = function() {
  var __args = arguments;
  if (__args.length === 0) {
    return utils.convReturnVertxGen(JDbService["create()"](), DbService);
  } else throw new TypeError('function invoked with invalid arguments');
};

/**

 @memberof module:service-db-js/db_service
 @param vertx {Vertx} 
 @param address {string} 
 @return {DbService}
 */
DbService.createProxy = function(vertx, address) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
    return utils.convReturnVertxGen(JDbService["createProxy(io.vertx.core.Vertx,java.lang.String)"](vertx._jdel, address), DbService);
  } else throw new TypeError('function invoked with invalid arguments');
};

// We export the Constructor function
module.exports = DbService;