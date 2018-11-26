package io.github.takecare.cv.experience

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
import kotlinx.android.synthetic.main.fragment_experience.*
import javax.inject.Inject

class ExperienceFragment : Fragment(), ExperienceView {

    companion object {
        fun newInstance() = ExperienceFragment()
    }

    @Inject
    lateinit var presenter: ExperiencePresenter

    @Inject
    lateinit var snackbarDislpayer: SnackbarDisplayer

    @Inject
    lateinit var adapter: ExperienceAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies(context)
    }

    private fun injectDependencies(context: Context) {
        CvApplication.get(context)
            .componentProvider
            .experienceComponent(context)
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_experience, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.startPresenting(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    override fun show(experienceViewModel: ExperienceViewModel) {
        adapter.update(experienceViewModel.items)
    }

    override fun showError(throwable: Throwable) {
        val message = throwable.message ?: resources.getString(R.string.default_error_message)
        view?.let { snackbarDislpayer.display(it, message) }
    }
}
