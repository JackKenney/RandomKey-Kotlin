import SwiftUI
import shared

class Model {
    var state = KeySelector().sampleKey()
}

struct ContentView: View {
    let selector = KeySelector()
    var model = Model()
    
    func updateKey() {
        model.state = selector.sampleKey()
    }

	var body: some View {
        Button(action: self.updateKey) {
            Text(model.state)
                .font(Font.custom("SFUIDisplay-Light", size: 150))
                .fontWeight(.bold)
                .multilineTextAlignment(.center)
                .accessibilityLabel(model.state)
                .accessibilityIdentifier("Key")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
