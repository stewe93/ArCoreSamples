package hu.stewe93.arcoresamples.ui.main

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import hu.stewe93.arcoresamples.R
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private var yodaModel: ModelRenderable? = null
    lateinit var arFragment: ArFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arFragment = childFragmentManager.findFragmentById(R.id.arView) as ArFragment

        loadModel()

    }

    private fun initTapListener() {
        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            val anchorNode = AnchorNode(
                hitResult.createAnchor()
            )

            anchorNode.setParent(arFragment.arSceneView.scene)
            val yodaNode = Node()
            yodaNode.renderable = yodaModel
            yodaNode.setParent(anchorNode)
        }
    }

    private fun loadModel() {
        lifecycleScope.launch {
            yodaModel = ModelRenderable
                .builder()
                .setSource(
                    context,
                    Uri.parse("scene.sfb")
                )
                .build()
                .await()
            Toast.makeText(
                requireContext(),
                "Model available",
                Toast.LENGTH_SHORT
            ).show()
            initTapListener()
        }
    }

}