package co.aladinjunior.inadipay.main.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.aladinjunior.inadipay.R
import co.aladinjunior.inadipay.data.db.entities.ActiveWallet
import co.aladinjunior.inadipay.data.db.entities.Costumer
import co.aladinjunior.inadipay.util.App

class ActiveWalletFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_active_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val walletView = view.findViewById<TextView>(R.id.wallet)



        Thread {
            val app = requireActivity().application as App
            val dao = app.db.costumerDao()
            val wallet = app.db.activeWalletDao()

            // Verifique se a carteira existe no banco de dados
            val existingWallet = wallet.getActiveWallet()
            wallet.updateTotalAmount(dao.getAllRemainingValue())



            //Verifique os valores do remainingValue





            if (existingWallet == null) {
                // A carteira não existe, você pode criar um novo registro se necessário
                val newWallet = ActiveWallet(wallet = 0.0)
                wallet.insert(newWallet)
            } else {
                // A carteira existe, atualize a exibição com o valor existente
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), existingWallet.wallet.toString(), Toast.LENGTH_SHORT).show()
                    walletView.visibility = View.VISIBLE
                    val walletResponse = getString(R.string.total_amount, existingWallet.wallet.toString())
                    walletView.text = walletResponse
                }
            }
        }.start()
    }
}