package io.github.takecare.cv.cover

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.takecare.SnackbarDisplayer
import io.github.takecare.cv.CvApplication
import io.github.takecare.cv.R
import kotlinx.android.synthetic.main.fragment_cover.*
import javax.inject.Inject

class CoverFragment : Fragment(), CoverView {

    companion object {
        fun newInstance() = CoverFragment()
    }

    @Inject
    lateinit var presenter: CoverPresenter

    @Inject
    lateinit var snackbarDislpayer: SnackbarDisplayer

    private lateinit var coverAdapter: CoverAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies(context)
    }

    private fun injectDependencies(context: Context) {
        CvApplication.get(context)
            .componentProvider
            .coverComponent(activity!!)
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cover, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        coverAdapter = CoverAdapter(presenter)
        cover_recyclerview.layoutManager = LinearLayoutManager(activity)
        cover_recyclerview.adapter = coverAdapter
    }

    override fun onStart() {
        super.onStart()
        presenter.startPresenting(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    override fun show(coverViewModel: CoverViewModel) {
        coverAdapter.update(coverViewModel.items)
    }

    override fun showError(throwable: Throwable) {
        val message = throwable.message ?: resources.getString(R.string.default_error_message)
        view?.let { snackbarDislpayer.display(it, message) }
    }
}
