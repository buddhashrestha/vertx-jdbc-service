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

/** @module service-database-js/db_service */
!function (factory) {
  if (typeof require === 'function' && typeof module !== 'undefined') {
    factory();
  } else if (typeof define === 'function' && define.amd) {
    // AMD loader
    define('service-database-js/db_service-proxy', [], factory);
  } else {
    // plain old include
    DbService = factory();
  }
}(function () {

  /**
 This is the interface for NuProcess Implementation. Created by pcjoshi on 11/27/15.

 @class
  */
  var DbService = function(eb, address) {

    var j_eb = eb;
    var j_address = address;
    var closed = false;
    var that = this;
    var convCharCollection = function(coll) {
      var ret = [];
      for (var i = 0;i < coll.length;i++) {
        ret.push(String.fromCharCode(coll[i]));
      }
      return ret;
    };

    /**

     @public
     @param procName {string} 
     @param databaseConfig {Object} 
     @param resultHandler {function} 
     */
    this.storedProc = function(procName, databaseConfig, resultHandler) {
      var __args = arguments;
      if (__args.length === 3 && typeof __args[0] === 'string' && typeof __args[1] === 'object' && typeof __args[2] === 'function') {
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"procName":__args[0], "databaseConfig":__args[1]}, {"action":"storedProc"}, function(err, result) { __args[2](err, result &&result.body); });
        return;
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"query":__args[0], "databaseConfig":__args[1]}, {"action":"createStm"}, function(err, result) { __args[2](err, result &&result.body); });
        return;
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"query":__args[0], "databaseConfig":__args[1]}, {"action":"update"}, function(err, result) { __args[2](err, result &&result.body); });
        return;
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"query":__args[0], "databaseConfig":__args[1]}, {"action":"delete"}, function(err, result) { __args[2](err, result &&result.body); });
        return;
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"query":__args[0], "databaseConfig":__args[1]}, {"action":"read"}, function(err, result) { __args[2](err, result &&result.body); });
        return;
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
        if (closed) {
          throw new Error('Proxy is closed');
        }
        j_eb.send(j_address, {"query":__args[0], "databaseConfig":__args[1]}, {"action":"nonSharedRead"}, function(err, result) { __args[2](err, result &&result.body); });
        return;
      } else throw new TypeError('function invoked with invalid arguments');
    };

  };

  /**

   @memberof module:service-database-js/db_service

   @return {DbService}
   */
  DbService.create = function() {
    var __args = arguments;
    if (__args.length === 0) {
      if (closed) {
        throw new Error('Proxy is closed');
      }
      j_eb.send(j_address, {}, {"action":"create"});
      return;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**

   @memberof module:service-database-js/db_service
   @param vertx {Vertx} 
   @param address {string} 
   @return {DbService}
   */
  DbService.createProxy = function(vertx, address) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && typeof __args[1] === 'string') {
      if (closed) {
        throw new Error('Proxy is closed');
      }
      j_eb.send(j_address, {"vertx":__args[0], "address":__args[1]}, {"action":"createProxy"});
      return;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  if (typeof exports !== 'undefined') {
    if (typeof module !== 'undefined' && module.exports) {
      exports = module.exports = DbService;
    } else {
      exports.DbService = DbService;
    }
  } else {
    return DbService;
  }
});