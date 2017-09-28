package net.corda.notarydemo

import net.corda.cordform.CordformContext
import net.corda.cordform.CordformDefinition
import net.corda.core.internal.div
import net.corda.nodeapi.internal.ServiceInfo
import net.corda.testing.ALICE
import net.corda.testing.BOB
import net.corda.testing.DUMMY_NOTARY
import net.corda.testing.internal.demorun.*

fun main(args: Array<String>) = CustomNotaryCordform.runNodes()

object CustomNotaryCordform : CordformDefinition("build" / "notary-demo-nodes", DUMMY_NOTARY.name.toString()) {
    init {
        node {
            name(ALICE.name)
            p2pPort(10002)
            rpcPort(10003)
            rpcUsers(notaryDemoUser)
        }
        node {
            name(BOB.name)
            p2pPort(10005)
            rpcPort(10006)
        }
        node {
            name(DUMMY_NOTARY.name)
            p2pPort(10009)
            rpcPort(10010)
            advertisedServices(ServiceInfo(MyCustomValidatingNotaryService.type))
        }
    }

    override fun setup(context: CordformContext) {}
}