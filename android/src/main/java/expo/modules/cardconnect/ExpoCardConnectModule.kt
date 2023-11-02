package expo.modules.cardconnect

import com.cardconnect.consumersdk.CCConsumer;
import com.cardconnect.consumersdk.CCConsumerTokenCallback;
import com.cardconnect.consumersdk.domain.CCConsumerAccount;
import com.cardconnect.consumersdk.domain.CCConsumerCardInfo;
import com.cardconnect.consumersdk.domain.CCConsumerError;
import com.cardconnect.consumersdk.utils.CCConsumerCardUtils;

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoCardConnectModule : Module() {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoCardConnect')` in JavaScript.
    Name("ExpoCardConnect")

    Function("setEndpoint") { endpoint: String ->
      CCConsumer.getInstance().getApi().setEndpoint("https://" + url + "/cardsecure/cs");
      CCConsumer.getInstance().getApi().setDebugEnabled(true);
    }

    Function("getCardToken") { cardNumber: String, expiryDate: String, cvv: String, promise: Promise ->
      try {
        validateCardNumber(cardNumber)
        validateCvv(cvv)

        val mCCConsumerCardInfo = CCConsumerCardInfo()
        mCCConsumerCardInfo.cardNumber = cardNumber
        mCCConsumerCardInfo.expirationDate = expiryDate
        mCCConsumerCardInfo.cvv = cvv

        CCConsumer.getInstance().api.generateAccountForCard(mCCConsumerCardInfo, object : CCConsumerTokenCallback {
          override fun onCCConsumerTokenResponseError(ccConsumerError: CCConsumerError) {
            promise.reject(Exception(ccConsumerError.responseMessage))
          }

          override fun onCCConsumerTokenResponse(ccConsumerAccount: CCConsumerAccount) {
            promise.resolve(ccConsumerAccount.token)
          }
        })
      } catch (e: Exception) {
        promise.resolve(e)
        e.printStackTrace()
      }
    }

    // Enables the module to be used as a native view. Definition components that are accepted as part of
    // the view definition: Prop, Events.
    View(ExpoCardConnectView::class) {
      // Defines a setter for the `name` prop.
      Prop("name") { view: ExpoCardConnectView, prop: String ->
        println(prop)
      }
    }
  }
}
